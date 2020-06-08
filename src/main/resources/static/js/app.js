var App = function() {
    // Constantes
    var DURATION_ANIMATION = 250;

    // Variables
    var self = this;
    self.main = $('.main');
    self.navbar = $('#navbar');
    self.categories = $('#categories');
    self.items = $('.item-list .item');
    self.heading = $('#heading, .heading');
    self.cover = $('.cover');
    self.hasHeading = self.heading.length == 0 ? false : true;
    self.hasCover = self.cover.length == 0 ? false : true;
    self.headerHeight = self.hasHeading ? self.heading.height() : 0;
    self.starRating = $('.star-rating.updatable');
    self.inputFile = $('.file-input');
    self.checkbox = $('.checkbox');
    self.radio = $('.radio');
    self.cards = $('.card');
    self.footer = $('#footer');

    self.init = function() {
        $('.carousel').hover(function() {
            $(this).find('.carousel-control').animate({'opacity': 1}, DURATION_ANIMATION);
        }, function() {
            $(this).find('.carousel-control').animate({'opacity': 0}, DURATION_ANIMATION);
        });
        $('[data-toggle="tooltip"]').tooltip();
        self.categories.css('height', self.main.height() + 'px');
        $('.zoom').zoom();

        return self;
    };

    self.bind = function() {
        if (self.hasHeading) {
            $(window).on('scroll', function () {
                var scrollTop = $(this).scrollTop();
                if ($(window).width() > 768 && scrollTop > self.headerHeight - 125) {
                    self.navbar.removeClass('navbar-transparent');
                } else {
                    self.navbar.addClass('navbar-transparent');
                    self.heading.find('img, h1, h2').css('margin-top', (scrollTop / 2) + 'px');
                }
            }).trigger('scroll');
        }

        if (self.hasCover) {
            $(window).on('scroll', function() {
                var scrollTop = $(this).scrollTop();
                self.cover.css({
                    'top': (scrollTop / 2) + 'px',
                    'bottom': (0 - scrollTop / 2) + 'px'
                });
                self.footer.css('bottom', (0 - scrollTop / 2) + 'px');
            }).trigger('scroll');
        }

        $('.pictures .thumbnails img').on('click', function(){
            self.updateThumbnails(this);
        });

        self.categories.find('a').on('click', function(e){
            var here = $(this);
            $.each(self.items, function(k, v){
                var p = $(v).parent();
                if (here.data('category') == 'All') {
                    p.css('display', 'block').animate({'opacity': 1}, DURATION_ANIMATION);
                } else if (p.data('category') == here.data('category')){
                    p.css('display', 'block').animate({'opacity': 1}, DURATION_ANIMATION);
                } else {
                    p.animate({'opacity': 0}, DURATION_ANIMATION, function(){
                        $(this).css('display', 'none');
                    });
                }
            });
            e.preventDefault();
        });

        self.starRating.on('mousemove', function(e){
            var parentOffset = $(this).offset();
            var width = $(this).width();
            var percent = (e.pageX + 5 - parentOffset.left) / width * 100;
            // Rounding percent (default 0.5 => star number * part number in star)
            var modulo = percent % (5 * 2);

            var rateValue = $(this).find('.rate-value');
            rateValue.css('width', (percent - modulo)+"%").data('current-val', percent - modulo);
        }).on('mouseleave', function(){
            var rateValue = $(this).find('.rate-value');
            rateValue.css('width', rateValue.data('value')+"%");
        }).on('click', function() {
            var val = $(this).find('.rate-value').data('current-val');
            $(this).find('input').val(val);
            $(this).find('.rate-value').data('value', val).css('width', val+'px');
        });

        self.inputFile.find('input').on('change', function(){
            var txt = '';
            if (this.files.length > 0) {
                for (i in this.files) {
                    if (typeof(this.files[i]) == 'object') {
                        txt += this.files[i]['name'] + ' | ';
                    }
                }
                txt = txt.substring(0, txt.length - 3);
            }
            if (txt != '') {
                txt = '<div class="relative">' +
                '<span>'+txt+'</span>' +
                '<div class="border"></div>' +
                '</div>';
            }
            $(this).parents('.file-input:first').find('.file-input-selected').html(txt).find('.border').animate({
                'left': 0,
                'right': 0
            }, 250);
        });

        self.cards.find('.card-title .fa-bars').on('click', function() {
            $(this).parents('.card:first').find('.card-reveal').toggleClass('in');
        });

        self.checkbox.find('input[type="checkbox"]').on('change', function() {
            if ($(this).is(':checked')) {
                $(this).parents('.checkbox:first').find('i').attr('class', 'fa fa-check-square');
            } else {
                $(this).parents('.checkbox:first').find('i').attr('class', 'fa fa-square-o');
            }
        }).trigger('change');

        self.radio.find('input[type="radio"]').on('change', function() {
            $.each(self.radio.find('input[type="radio"][name="'+this.name+'"]'), function(k, v) {
                if ($(v).is(':checked')) {
                    $(v).parents('.radio:first').find('i').attr('class', 'fa fa-dot-circle-o');
                } else {
                    $(v).parents('.radio:first').find('i').attr('class', 'fa fa-circle-o');
                }
            });
        }).trigger('change');

        return self;
    };

    self.updateThumbnails = function(obj) {
        $(obj).parent().append($('.pictures .primary img').css('visibility', 'visible'));
        var zoom = $('<div>').attr({
            'class': 'zoom',
            'data-zoom': $(obj).attr('src')
        }).html($(obj));
        $('.pictures .primary').html(zoom).find('.zoom').zoom();
        $('.pictures .thumbnails img').on('click', function(){
            self.updateThumbnails(this);
        });
    };

    return self.bind().init();
};

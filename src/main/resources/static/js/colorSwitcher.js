var ColorSwitcher = function(){
    var self = this;
    self.baliseLink = $('link[href*="app-blue"]');
    self.switcher = null;
    self.currentColor = 'blue';
    self.isSized = $('#isSized').val() == 1 ? true : false;
    self.colors = ['blue', 'red', 'yellow', 'green', 'pink', 'white'];
    self.css = {
        '#switcher': 'position: fixed; top: 150px; right: -111px; z-index: 1000;',
        '.switcher-container': 'position: relative; width: 148px; height: 50px;',
        '.color-selected': 'width: 35px; padding: 10px 0 0 10px; position: absolute; z-index: 12; top: 5px; left: 5px; background-color: #eee; border: dashed 1px #999; border-right: none; border-top-left-radius: 4px; border-bottom-left-radius: 4px;',
        '.colors': 'width: 111px; padding: 10px 0 0 10px; position: absolute; top: 5px; left: 39px; background-color: #eee; border: dashed 1px #999; border-right: none; z-index: 11; border-bottom-left-radius: 4px;',
        '.color-': 'width: 20px; height: 20px; border: solid 1px #ccc; float: left; margin-right: 10px; margin-bottom: 10px; border-radius: 4px; cursor: pointer;',
        '.color-class': {
            'blue': 'background-color: #2196F3;',
            'red': 'background-color: #F44336;',
            'yellow': 'background-color: #FFEB3B;',
            'green': 'background-color: #4CAF50;',
            'pink': 'background-color: #9C27B0;',
            'white': 'background-color: #ffffff;'
        },
        'a': 'color: #555'
    };

    self.init = function() {
        var colorsHtml = '';
        for (color in self.colors) {
            if (self.colors[color] != self.currentColor) {
                colorsHtml += '<div style="' + self.css['.color-'] + self.css['.color-class'][self.colors[color]] + '" class="color color-'+self.colors[color]+'" data-color="' + self.colors[color] + '"></div>';
            }
        }

        self.switcher = $('<div>').attr('id', 'switcher').attr('style', self.css['#switcher']).html(
            '<div style="'+self.css['.switcher-container']+'">'+
                '<div class="color-selected" style="'+self.css['.color-selected']+'">' +
                    '<div class="color color-'+self.currentColor+'" data-color="'+self.currentColor+'" style="'+self.css['.color-']+self.css['.color-class'][self.currentColor]+'"></div>' +
                '</div>' +
                '<div class="colors" style="'+self.css['.colors']+'">' +
                    '<div class="color-container">' +
                        colorsHtml +
                    '</div>' +
                    '<div class="clearfix"></div>' +
                    '<div class="margin-top-10 text-center">' +
                        '<a href="?fixedWidth='+(self.isSized ? '0' : '1')+'" style="'+self.css['&']+'">' +
                            (self.isSized ? 'See "fullsize" version' : 'See "fixed&nbsp;width" version') +
                        '</a>' +
                    '</div>' +
                '</div>'+
            '</div>'
        ).hover(function(){
            self.switcher.animate({'right': 0}, 250);
        }, function(){
            self.switcher.animate({'right': -111+'px'}, 250);
        });
        self.switcher.find('.color').on('click', function() {
            var colorSelected = $(this).data('color');
            if (self.currentColor != colorSelected) {
                self.baliseLink.attr('href', self.baliseLink.attr('href').replace('app-'+self.currentColor, 'app-'+colorSelected));
                self.currentColor = colorSelected;
                self.switcher.find('.color-selected .color').appendTo(self.switcher.find('.colors .color-container'));
                $(this).appendTo(self.switcher.find('.color-selected'));
                self.save();
            }
        });
        $('body').append(self.switcher);

        return self;
    };

    self.save = function() {
        if (localStorage) {
            localStorage.setItem('color', self.currentColor);
        }

        return self;
    };

    self.load = function() {
        if (localStorage) {
            var color = localStorage.getItem('color');
            self.switcher.find('.color-'+color).trigger('click');
        }

        return self;
    };

    return self.init().load();
};
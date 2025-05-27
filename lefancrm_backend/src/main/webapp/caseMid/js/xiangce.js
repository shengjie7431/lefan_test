$(function() {
    var firstpic,
        firstsrc,
        oId = 0;
    var ohref = window.location.search,
        arrSearch = ohref.slice(1).split('&');
    var $list = $(".detail_picbot_mid ul li")
    var dNumber = 0
    for (var i = 0,len = arrSearch.length; i < len; i++) {
        if (arrSearch[i].split('=')[0] === 'index') {
            oId = Number(arrSearch[i].split('=')[1])
        }
    }
    if (oId >0) {
        firstpic = $list.eq(oId).find("img");
        for (var i = 0; i < oId - 4; i++) {
            $list.eq(i).css("display", "none");
        }
        $list.eq(oId).css({
            "width": "94px",
            "display": "block"
        })
    } else {
        firstpic = $list.first().find("img");
    }
    firstsrc = firstpic.attr("bigimg");
    $("#pic1").attr({
        "src": firstsrc,
        "curindex": oId
    });
    firstpic.addClass("selectpic");
    var $dcpt = $('.detail_context_pic_top')
    var _width = $dcpt.width();
    var _height = $dcpt.height();

    // setWH();
    _hover('#preArrow', '#preArrow_A');
    _hover('#nextArrow', '#nextArrow_A');

    $("#preArrow, #preArrow_B").click(function() {
        preclick()
    });
    $("#nextArrow, #nextArrow_B").click(function() {
        nextclick()
    });
    // $('.p_img').on('mousewheel',function(e){
    // 	bigImg(this);
    // 	setMargin(dNumber)
    // })
    $('.p_restore').on('click', function(){
        $('.p_img').attr('style', '')
        iconChange = 100
    })
    $(".detail_picbot_mid ul li").click(function() {
        var currentLiIndex = $(this).index(".detail_picbot_mid ul li");
        $(".detail_picbot_mid ul li img[class='selectpic']").removeClass("selectpic");
        var curnextLi = $(".detail_picbot_mid ul li").eq(currentLiIndex);
        curnextLi.find("img").addClass("selectpic");
        var bigMmgSrc = curnextLi.find("img").attr("bigimg");
        $("#pic1").attr({
            "src": bigMmgSrc,
            "curindex": currentLiIndex,
            "style": ''
        });
        dNumber = 0;
        setWH();
    });
    $('.rotateCW').on('click', function () {
        dNumber = dNumber + 90
        rotate(dNumber)
    })
    $('.rotateAW').on('click', function () {
        dNumber = dNumber - 90
        rotate(dNumber)
    })
    $('.magnify').on('click', function(e) {
        var curIndex = $('#pic1').attr('curindex');
        var bigImgSrc = $(".detail_picbot_mid ul li").eq(curIndex).find("img").attr("bigimg");
        $("#pic2").find('img').attr({
            "src": bigImgSrc,
            "curindex": curIndex,
            "style": '',
            "data-flag": true
        });
        $("#pic2").show();
        $('#pic1').attr('style', '');
        dNumber = 0;
    })
    $('.toolsMax .icon-to-close').on('click', function () {
        $("#pic2").hide();
        $("#pic2").find('img').attr({
            "data-flag": ''
        })
        $('.p_img').attr('style', '')
        iconChange = 100
        // $('.p_img').css('zoom', iconChange + '%')
    })
    var iconChange = 100,
        changeValue = 20
    $('.toolsMax .icon-to-big').on('click', function(){
        iconChange = iconChange + changeValue
        $('.p_img').css('zoom', iconChange + '%')
        setMargin(dNumber)
    })
    $('.toolsMax .icon-to-small').on('click', function(){
        if (iconChange > changeValue) {
            iconChange = iconChange - changeValue
        }
        $('.p_img').css('zoom', iconChange + '%')
        setMargin(dNumber)
    })

    viewBigImage('.picToBig', '#pic_bigimg')
    function viewBigImage (paramA, paramB) {
        if (!document.getElementById(paramB.slice(1))) {
            document.body.insertAdjacentHTML('beforeend', '<div id="' + paramB.slice(1) + '"><div class="pic_big_content"><img src="" /></div><div class="icon-tools"><a href="javascript:;" class="icon-to-big" title ="放大"></a><a href="javascript:;" class="icon-to-small" title ="缩小"></a><a href="javascript:;" class="icon-to-close" title ="关闭"></a></div></div>');
        }
        var a = getElem (paramA),
            b = getElem (paramB)
        if (b) {
            b.style.cssText = "display: none;position:fixed;top: 0;width: 100%;height: 100%;z-index:99;overflow: auto;background-color: rgba(0, 0, 0, 0.6);"
            b.getElementsByTagName('img')[0].style.cssText = "display: block; margin: 10px auto;  width: auto;height: auto; "
            b.getElementsByClassName('icon-tools')[0].style.cssText = "overflow: hidden;position: fixed;display: flex;justify-content: space-around;right: 50px;top: 50px;width: 200px;height: 50px;"
            // b.getElementsByTagName('a')[0].style.cssText = "position: relative;box-sizing: border-box;width: 50px;height: 50px;border: 3px dashed #e6e6e6;border-radius: 50%;"
            // window.getComputedStyle(b.getElementsByClassName('icon-to-big')[0], ":before").style.cssText = " content: '';position: absolute;width: 30px;height: 2px;left: 50%;top: 50%;margin-left: -15px;margin-top: -1px;background-color: #e6e6e6;"
            // window.getComputedStyle(b.getElementsByClassName('icon-to-big')[0], ":after").style.cssText = "content: '';position: absolute;width: 2px;height: 30px;left: 50%;top: 50%;margin-left: -1px;margin-top: -15px;background-color: #e6e6e6;"
            // window.getComputedStyle(b.getElementsByClassName('icon-to-small')[0], ":before").style.cssText = " content: '';position: absolute;width: 30px;height: 2px;left: 50%;top: 50%;margin-left: -15px;margin-top: -1px;background-color: #e6e6e6;"
            if (paramA.slice(0,1) === '.') {
                for (var i = 0,len = a.length; i < len; i++) {
                    a[i].onclick = function(e){
                        var bigImgSrc = this.src
                        b.getElementsByTagName('img')[0].src = bigImgSrc
                        b.style.display = 'block'
                    }
                }
            } else {
                a.onclick = function(e){
                    var bigImgSrc = this.src
                    b.getElementsByTagName('img')[0].src = bigImgSrc
                    b.style.display = 'block'
                }
            }
            moveImg(b.getElementsByTagName('img')[0], true)
            b.getElementsByClassName('icon-to-close')[0].onclick = function(){
                b.style.display = 'none'
                iconChange = 100
            }
            b.getElementsByClassName('icon-to-big')[0].onclick = function () {
                iconChange = iconChange + changeValue
                b.getElementsByClassName('pic_big_content')[0].style.zoom = iconChange + '%'
            }
            b.getElementsByClassName('icon-to-small')[0].onclick = function(){
                if (iconChange > changeValue) {
                    iconChange = iconChange - changeValue
                }
                b.getElementsByClassName('pic_big_content')[0].style.zoom = iconChange + '%'
            }

        }
        function getElem (obj) {
            var el,num
            if (obj.slice(0,1) === '#') {
                el = document.getElementById(obj.slice(1))
            } else if (obj.slice(0,1) === '.') {
                el = document.getElementsByClassName(obj.slice(1))
            } else {
                el = document.getElementsByTagName(obj)
            }
            return el
        }
        function getDivPro (obj) {
            var el =''
            if (obj.slice(0,1) === '#') {
                el = 'id ="' + obj.slice(1) + '"'
            } else if (obj.slice(0,1) === '.') {
                el = 'class ="' + obj.slice(1) + '"'
            }
            return el
        }
    }

    //根据一定比例缩小图片
    function setWH () {
        var bigMmgSrc = $(".detail_picbot_mid ul li img[class='selectpic']").attr('src')
        var imageN = getNaturalWH(bigMmgSrc);
        var nwidth = imageN[0],
            nheight = imageN[1],
            width,
            height;
        if (nwidth > _width) {
            if ( nheight > _height) {
                if (nwidth < nheight) {
                    width = _width
                    height = _height * nheight / nwidth
                } else {
                    width = _height * nwidth / nheight
                    height = _height
                }
                if (height > _height) {
                    width = _height * nwidth / nheight
                    height = _height
                }
            } else {
                width = _width
                height = _width * nheight / nwidth
            }
        } else {
            if ( nheight > _height) {
                width =nwidth * _height / nheight
                height = _height
            } else {
                width = _width
                height = _height
            }
        }
        $('#pic1').attr({
            'width': width + 'px',
            'height': height + 'px'
        })
    }
    function _hover(paramA, paramB) {
        $(paramA).hover(function() {
            $(paramB).css("display", "block")
        },function() {
            $(paramB).css("display", "none")
        });
    }
    function preclick() {
        var $lis = $(".detail_picbot_mid ul li")
        var currentIndex = parseFloat($("#pic1").attr("curindex"));
        if (currentIndex != 0) {
            var curli = $lis.eq(currentIndex);
            if (currentIndex <= ($lis.length - 5)) {
                $lis.eq(currentIndex + 4).css("display", "none");
                $lis.eq(currentIndex - 1).css({
                    "width": "94px",
                    "display": "block"
                })
            }
            var curnextLi = $lis.eq(currentIndex - 1);
            var curnextSrc = curnextLi.find("img").attr("bigimg");
            curli.find("img").removeClass("selectpic");
            curnextLi.find("img").addClass("selectpic");
            $("#pic1").attr({
                "src": curnextSrc,
                "curindex": currentIndex - 1,
                'style': ''
            });
        }
        dNumber = 0;
        setWH();
    }
    function nextclick() {
        var $lis = $(".detail_picbot_mid ul li")
        var currentIndex = parseFloat($("#pic1").attr("curindex"));
        if (currentIndex != ($lis.length - 1)) {
            var curli = $lis.eq(currentIndex);
            if (currentIndex > 3) {
                $lis.eq(currentIndex - 4).css("display", "none");
                $lis.eq(currentIndex + 1).css({
                    "width": "94px",
                    "display": "block"
                })
            }
            var curnextLi = $lis.eq(currentIndex + 1);
            var curnextSrc = curnextLi.find("img").attr("bigimg");
            curli.find("img").removeClass("selectpic");
            curnextLi.find("img").addClass("selectpic");
            $("#pic1").attr({
                "src": curnextSrc,
                "curindex": currentIndex + 1,
                'style': ''
            });
        }
        dNumber = 0;
        setWH();
    }
    function rotate (number) {
        if (!$('#pic2').find('img').attr('data-flag')) {
            $('#pic1').css({
                'transform': 'rotate(' + dNumber +'deg)',
                '-ms-transform': 'rotate(' + dNumber +'deg)',
                '-webkit-transform': 'rotate(' + dNumber +'deg)'})
        } else {
            $('#pic2').find('img').css({
                'transform': 'rotate(' + dNumber +'deg)',
                '-ms-transform': 'rotate(' + dNumber +'deg)',
                '-webkit-transform': 'rotate(' + dNumber +'deg)'})
            setMargin(dNumber)

        }
    }
    function setMargin (dNumber) {
        if (Math.abs(dNumber) % 180 === 90) {
            var pwidth = $('#pic2_img').width()
            var pheight = $('#pic2_img').height()
            var width2 = (pwidth - pheight) / 2
            var mleft = $('#pic2').find('img').css('marginLeft').replace('px', '')
            mleft = Number(mleft)
            var mtop = $('#pic2').find('img').css('marginTop').replace('px', '')
            mtop = Number(mtop)
            if (width2 > 0 && width2 > mtop) {
                $('#pic2').find('img').css({
                    'margin-top': width2 + 'px'
                })
            } else if (width2 < 0 && Math.abs(width2) > mleft) {
                $('#pic2').find('img').css({
                    'margin-left': Math.abs(width2) + 'px'
                })
            }

        } else {
            $('#pic2').find('img').css({
                'margin-top':'',
                'margin-left': ''
            })
        }
    }
    function bigImg(obj){
        var zoom = parseInt(obj.style.zoom, 10)||100;
        zoom += event.wheelDelta / 12;
        if (zoom > 0)
            obj.style.zoom=zoom+'%';
        return false;
    }
    function getNaturalWH(src) {
        var image = new Image();
        image.src = src;
        return [image.width,image.height];
    }
    var stop = 0
    moveImg($('#pic2_img'))
    function moveImg(obj, flag) {
        if ($ && jQuery && !flag) {
            obj.on('mousedown', function (e){
                var _this = $(this)
                _this.css('cursor', 'pointer')
                e.preventDefault();
                stop++
                var _this = $(this)
                var startPosition = getPosition(e)
                var mleft = _this.css('marginLeft') || 0
                mleft = Number(mleft.replace('px', ''))
                var mtop = _this.css('marginTop') || 0
                mtop = Number(mtop.replace('px', ''))
                _this.on('mousemove', function (e){
                    var curPosition = getPosition(e)
                    x = curPosition[0] - startPosition[0]
                    y = curPosition[1] - startPosition[1]
                    if (stop) {
                        _this.css({
                            'marginTop': mtop + y,
                            'marginLeft': mleft + x
                        })
                    }
                })

            })
            obj.on('mouseup', function (e){
                $(this).off('mousemove')
                stop = 0
            })
            $('.p_content').on('mouseover', function (e){
                obj.off('mousemove')
                stop = 0
            })
        } else {
            obj.onmousedown = function (e){
                var e = e || window.event;
                var target = e.target || e.srcElement;
                var _this = this
                target.style.cursor = 'pointer'
                e.preventDefault();
                stop++
                var startPosition = getPosition(e)
                var mleft = getStyle(obj, 'margin-left') || '0px'
                mleft = Number(mleft.replace('px', ''))
                var mtop =  getStyle(obj, 'margin-top')  || '0px'
                mtop = Number(mtop.replace('px', ''))
                obj.onmousemove = function (e){
                    var curPosition = getPosition(e)
                    x = curPosition[0] - startPosition[0]
                    y = curPosition[1] - startPosition[1]
                    var xx = mtop + y + 'px',
                        yy = mleft + x + 'px'
                    if (stop) {
                        obj.style.marginLeft = yy
                        obj.style.marginTop  = xx
                    }
                }

            }
            obj.onmouseup = function (e){
                this.onmousemove = null
                stop = 0
            }
            // $('.p_content').on('mouseover', function (e){
            // 	obj.off('mousemove')
            // 	stop = 0
            // })
        }
    }
    function getStyle(obj,style){
        if(obj.currentStyle){
            return obj.currentStyle.getAttribute(style);
        }
        if (window.getComputedStyle) {
            return window.getComputedStyle(obj, null).getPropertyValue(style);
        }
    }
    function getPosition (e) {
        var oEvent = e||event;
        //兼容性处理
        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        var scrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
        return [oEvent.clientX + scrollTop, oEvent.clientY + scrollLeft]
    }
    // function insertRule(sheet,ruleKey,ruleValue,index){
    // 	　　return sheet.insertRule ? sheet.insertRule(ruleKey+ '{' + ruleValue + '}',index) : sheet.addRule(ruleKey,ruleValue,index);
    //   　　}
    // 	insertRule(document.styleSheets[0],'#box:before','content:"前缀";color: red;',0)
});


<style lang="less" >
@import "../../../node_modules/slick-carousel/slick/slick.less";
@import "../../../node_modules/slick-carousel/slick/slick-theme.less";
// @font-face {
//   font-family: 'Pacifico';
//   src: url('pacifico.eot');
//   src: url('pacifico.eot?#iefix') format('embedded-opentype'),
//        url('pacifico.woff') format('woff'),
//        url('pacifico.ttf') format('truetype'),
//        url('pacifico.svg#Pacifico') format('svg');
// }


.blue{
  // background:#3498db;
  color:#fff;

.blue h3{background:#fff;color:#3498db;font-size:36px;line-height:100px;margin:0px;padding:2%;position:relative;text-align:center;}
.variable-width .slick-slide p { background: #fff; height: 100px; color:#3498db; margin: 5px; line-height: 100px; }
.button{background:#3498db;color:#fff;display:block;font-size:16px;margin:20px auto;padding:20px;text-align:center;text-decoration:none;width:48%;}
.buttons{padding:0 20px 20px; margin-bottom: 10px;}
.buttons .button{background:#FFF;color:#3498db;float:left;margin:5px;}
.center .slick-center h3{-moz-transform:scale(1.08);-ms-transform:scale(1.08);-o-transform:scale(1.08);-webkit-transform:scale(1.08);color:#e67e22;opacity:1;transform:scale(1.08);}
.center h3{opacity:0.8;transition:all 300ms ease;}
.content{margin:auto;padding:20px;width:600px;}
.content:after,.buttons::after{clear:both;content:"";display:table;}
.destroy{font-weight:400;margin-top:40px;}
.features{display:block;list-style-type:none;margin-top:30px;padding:0;text-align:center;}
.features li{margin:20px 0;}
.filter .button{background:#FFF;color:#3498db; margin-bottom: 40px;}
.fixed-header{background:#FFF;box-shadow:2px 0 5px rgba(0,0,0,0.5);display:none;padding:10px;position:fixed;top:0;width:100%;z-index:10000;}
.fixed-header .header-content{margin:auto;width:600px;}
.fixed-header .subheading{display:none;}
.fixed-header h1.title{float:left;font-size:24px;margin:0;}
.fixed-header ul.nav{float:right;margin:0;padding:5px;}
.fixed-header ul.nav li{margin:0 0 0 10px;}
.header{padding:20px 0;}
.margin-40{margin-bottom:40px;}
.more,.button.first{margin-top:40px;}
.red{background:#e74c3c;color:#fff;}
.slick-slide .image{padding:10px;}
.slick-slide img{border:5px solid #FFF;display:block;width:100%;}
.slick-slide img.slick-loading{border:0 }
.slick-slider{margin:30px auto 20px;}
.subheading{color:#555;font-size:12px;font-style:italic;font-weight:400;margin:10px auto;text-align:center;}
.white{background:#fff;color:#3498db;}
.white pre,.white hr{background:#3498db !important;}
a{color:#3498db;}
body,html{-moz-osx-font-smoothing:grayscale;-webkit-font-smoothing:antialiased;background:#fff; font-family: "Lato","Helvetica Neue","Helvetica",Helvetica,Arial,sans-serif; height:100%;line-height:1;margin:0;padding:0;text-rendering:optimizeLegibility;width:100%;}
code{color:#000; overflow-x: scroll;}
h1{color:#3498db;font-family:Pacifico;font-size:72px;font-weight:400;line-height:1.2;margin:0 auto 10px;text-align:center;}
h1.title{font-size:96px;}
h2{font-family:Pacifico;font-size:36px;margin:20px auto;text-align:center;}
h4{font-family:Pacifico;font-size:28px;margin:20px auto;text-align:center;}
hr{background:#fff;border:0;height:1px;margin:40px 0;}
p{font-size:16px;line-height:1.5;margin-top: 30px;margin-bottom:40px;text-align:center;color:#333;}
p.note{font-size: 12px;}
p.cdn{font-size: 14px;}
p.guff{font-size:16px;}
pre{background:#fff;margin:0 10px 20px;padding:10px;}
section{width:100%; position: relative;}
table{font-size:14px;line-height:18px;margin:40px auto 20px; display: block;}
tr { width: 100%; border-right: none; border-bottom: 1px solid #fff; margin: 0px 0px 20px; padding: 0px 0px 20px; background: transparent; float: left; }
thead { display: none; }
td { border: 0; padding: 10px 0px;}
td,tbody { display: block; width: 100% !important;}
table.settings td:nth-of-type(1), table.methods td:nth-of-type(1){font-weight: 700; font-size: 16px; line-height: 18px;}
table.settings td:nth-of-type(2):before{content: 'Type: '; font-weight: 700;}
table.settings td:nth-of-type(3):before{content: 'Default: '; font-weight: 700;}
table.methods td:nth-of-type(2):before{content: 'Arguments: '; font-weight: 700;}
ul.nav{margin-bottom:0;padding-left:0;text-align:center;}
ul.nav li{display:inline-block;list-style-type:none;margin:0 20px;}
ul.nav li a{font-size:14px;text-decoration:none;}
#carbonads { max-width: 320px; width: 100%; color: white; background: white; padding: 20px 20px 20px; margin: 0px auto 40px; text-align: center; position: relative; top: 40px; }
#carbonads a { display: inline-block; margin-bottom: 0px; font-size: 10px; line-height: 1.5; }
#carbonads .carbon-img { margin-bottom: 10px; }
@media (max-width: 420px) {
ul.nav li a{display:block;font-size:14px;}
}
@media (max-width: 768px) {
.fixed-header { display: none !important; }
#carbonads { top: 0px; }
.header ul.nav li { display: block; margin: 20px; }
.blue h3{font-size:24px;}
.button{margin:0 auto 20px;width:auto;}
.button.first{margin-top:40px;}
.buttons{padding:0 0 20px;}
.buttons .button{float:left;font-size:12px;margin:1%;width:48%;}
.center{margin-left:0;margin-right:0;}
.center .slick-center h3{-moz-transform:scale(1);-ms-transform:scale(1);-o-transform:scale(1);-webkit-transform:scale(1);color:#e67e22;opacity:1;transform:scale(1);}
.center h3{padding: 4px;-moz-transform:scale(0.55);-ms-transform:scale(0.55);-o-transform:scale(0.55);-webkit-transform:scale(0.55);opacity:0.8;transform:scale(0.55);transition:all 300ms ease;}
.center h3 img{border-radius: 50%;-moz-box-shadow:1px 1px 5px #333333; -webkit-box-shadow:1px 1px 5px #333333; box-shadow:1px 1px 5px #333333;}
.content{margin:auto;padding:20px 40px;width:auto;}
.fixed-header .header-content{width:auto;}
pre{font-size:12px; overflow-x: scroll;}
table{font-size:14px;line-height:18px;margin:40px auto 20px; display: block; float: left;}
tr { width: 100%; border-right: none; border-bottom: 1px solid #fff; margin: 0px 0px 20px; padding: 0px 0px 20px; background: transparent; float: left; }
thead { display: none; }
td { border: 0; padding: 10px 0px;}
td,tbody { display: block; width: 100% !important;}
table.settings td:nth-of-type(1), table.methods td:nth-of-type(1){font-weight: 700; font-size: 16px; line-height: 18px;}
table.settings td:nth-of-type(2):before{content: 'Type: '; font-weight: 700;}
table.settings td:nth-of-type(3):before{content: 'Default: '; font-weight: 700;}
table.methods td:nth-of-type(2):before{content: 'Arguments: '; font-weight: 700;}
}



/* http://prismjs.com/download.html?themes=prism-coy&languages=markup+css+clike+javascript */
/**
 * prism.js Coy theme for JavaScript, CoffeeScript, CSS and HTML
 * Based on https://github.com/tshedor/workshop-wp-theme (Example: http://workshop.kansan.com/category/sessions/basics or http://workshop.timshedor.com/category/sessions/basics);
 * @author Tim  Shedor
 */

code[class*="language-"],
pre[class*="language-"] {
	color: white;
	font-family: Consolas, Monaco, 'Andale Mono', monospace;
	font-size: 12px;
	direction: ltr;
	text-align: left;
	white-space: pre;
	word-spacing: normal;
	word-break: normal;
	line-height: 1.5;

	-moz-tab-size: 4;
	-o-tab-size: 4;
	tab-size: 4;

	-webkit-hyphens: none;
	-moz-hyphens: none;
	-ms-hyphens: none;
	hyphens: none;
}

/* Code blocks */
pre[class*="language-"] {
	position: relative;
	margin: 0 10px 20px;
	padding: 10px;
	border: 1px solid #dfdfdf;
	background-color: #fdfdfd;
	background-size: 3em 3em;
	background-origin: content-box;
	overflow: visible;
}

code[class*="language"] {
	background: black;
	max-height: inherit;
	padding: 0px 15px;
	display: block;
	overflow: auto;
}

/* Margin bottom to accomodate shadow */
:not(pre) > code[class*="language-"],
pre[class*="language-"] {
	background-color: #fdfdfd;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	margin-bottom: 1em;
}

/* Inline code */
:not(pre) > code[class*="language-"] {
	position: relative;
	padding: .2em;
	-webkit-border-radius: 0.3em;
	-moz-border-radius: 0.3em;
	-ms-border-radius: 0.3em;
	-o-border-radius: 0.3em;
	border-radius: 0.3em;
	color: #c92c2c;
	border: 1px solid rgba(0, 0, 0, 0.1);
}

:not(pre) > code[class*="language-"]:after,
pre[class*="language-"]:after {
	right: 0.75em;
	left: auto;
	-webkit-transform: rotate(2deg);
	-moz-transform: rotate(2deg);
	-ms-transform: rotate(2deg);
	-o-transform: rotate(2deg);
	transform: rotate(2deg);
}

.token.comment,
.token.block-comment,
.token.prolog,
.token.doctype,
.token.cdata {
	color: #7D8B99;
}

.token.punctuation {
	color: white;
}

.token.property,
.token.tag,
.token.boolean,
.token.number,
.token.function-name,
.token.constant,
.token.symbol,
.token.deleted {
	color: #9b59b6;
}

.token.string {
	color: #e74c3c;
}

.token.selector,
.token.attr-name,
.token.char,
.token.function,
.token.builtin,
.token.inserted {
	color: #3498db;
}

.token.operator,
.token.entity,
.token.url,
.token.variable {
	color: white;
}

.token.atrule,
.token.attr-value,
.token.keyword,
.token.class-name {
	color: #f1c40f;
}

.token.regex,
.token.important {
	color: #e90;
}

.language-css .token.string,
.style .token.string {
	color: #e74c3c;
	background: rgba(255, 255, 255, 0.5);
}

.token.important {
	font-weight: normal;
}

.token.entity {
	cursor: help;
}

.namespace {
	opacity: .7;
}

@media screen and (max-width: 767px) {
	pre[class*="language-"]:before,
	pre[class*="language-"]:after {
		bottom: 14px;
		-webkit-box-shadow: none;
		-moz-box-shadow: none;
		box-shadow: none;
	}

}

/* Plugin styles */
.token.tab:not(:empty):before,
.token.cr:before,
.token.lf:before {
	color: #e0d7d1;
}

/* Plugin styles: Line Numbers */
pre[class*="language-"].line-numbers {
	padding-left: 0;
}

pre[class*="language-"].line-numbers code {
	padding-left: 3.8em;
}

pre[class*="language-"].line-numbers .line-numbers-rows {
	left: 0;
}
#sliderA.slick-slider{
  margin: 0;
}
}
.blue #sliderA {
   h3{
     margin: 0;
   }
   p{
     margin-bottom: 0
   }
}
.blue .slick-slide img{
  border:none;
}
.img-box{
  position: relative;
  .line1{
    height: 1px;
    width:60%;
    background: #ccc;
    margin: 10px auto;
  }
  .img1{
    position: absolute;
    top:-20px;
    left:50%;
    width: 20px;
    height:20px;
    display: block;
    margin-left:-20px;
    background: #fff;
    padding: 10px;
  }
}
.blue {
  .info{
    padding: 20px 0 100px;
    p{
      font-size: 14px;
      color:#ccc;
      font-weight: normal;
      line-height: 20px;
      margin: 0;
      padding: 0
    }
  }
}
</style>

<template lang="html">

<div class="blue">
  <slick id="sliderA" class="slider slider-for" ref="slick" :options="slickOptions1">
      <div v-for="(list,index) in datas" :key="index"><h3><img :src="list.img" /></h3><p>{{list.solarName}}</p></div>
  </slick>
  <div class="content">

    <slick class="slider slider-nav center" ref="slick" :options="slickOptions2">
        <div v-for="(list,index) in datas" :key="index"><h3><img :src="list.solarImg" /></h3></div>
    </slick>
  </div>
  <div class="img-box">
    <div class="line1"></div>
    <img class="img1" src="../../assets/images/7.png" alt="">
  </div>
  <div class="info">
    <p>好柿连连柿饼产自富平石川河流域，</p>
    <p>自由百亩柿子基地，</p>
    <p>独特的气候、日照、土壤赋予极佳的品质。</p>
  </div>

</div>

</template>

<script>
import img1 from '../../assets/images/1.png';
import img2 from '../../assets/images/1.png';
import img3 from '../../assets/images/1.png';
import img4 from '../../assets/images/1.png';
import img5 from '../../assets/images/1.png';
import img6 from '../../assets/images/1.png';
import img7 from '../../assets/images/1.png';
import img8 from '../../assets/images/1.png';
import img9 from '../../assets/images/1.png';
import img10 from '../../assets/images/1.png';
import img11 from '../../assets/images/1.png';
import img12 from '../../assets/images/1.png';
import img13 from '../../assets/images/1.png';
import img14 from '../../assets/images/1.png';
import img15 from '../../assets/images/1.png';
import img16 from '../../assets/images/1.png';
import img17 from '../../assets/images/1.png';
import img18 from '../../assets/images/1.png';
import img19 from '../../assets/images/1.png';
import img20 from '../../assets/images/1.png';
import img21 from '../../assets/images/1.png';
import img22 from '../../assets/images/1.png';
import img23 from '../../assets/images/1.png';
import img24 from '../../assets/images/1.png';
import solar1 from '../../assets/images/solar1.png';
import solar2 from '../../assets/images/solar2.png';
import solar3 from '../../assets/images/solar3.png';
import solar4 from '../../assets/images/solar4.png';
import solar5 from '../../assets/images/solar5.png';
import solar6 from '../../assets/images/solar6.png';
import solar7 from '../../assets/images/solar7.png';
import solar8 from '../../assets/images/solar8.png';
import solar9 from '../../assets/images/solar9.png';
import solar10 from '../../assets/images/solar10.png';
import solar11 from '../../assets/images/solar11.png';
import solar12 from '../../assets/images/solar12.png';
import solar13 from '../../assets/images/solar13.png';
import solar14 from '../../assets/images/solar14.png';
import solar15 from '../../assets/images/solar15.png';
import solar16 from '../../assets/images/solar16.png';
import solar17 from '../../assets/images/solar17.png';
import solar18 from '../../assets/images/solar18.png';
import solar19 from '../../assets/images/solar19.png';
import solar20 from '../../assets/images/solar20.png';
import solar21 from '../../assets/images/solar21.png';
import solar22 from '../../assets/images/solar22.png';
import solar23 from '../../assets/images/solar23.png';
import solar24 from '../../assets/images/solar24.png';
import Slick from 'vue-slick';
export default {
    components: {
        Slick
    },
    data() {
        return {
            datas:[
              {
                'img':img1,
                'solarImg':solar1,
                'solarName':'立春'
              },
              {
                'img':img2,
                'solarImg':solar2,
                'solarName':'雨水'
              },
              {
                'img':img3,
                'solarImg':solar3,
                'solarName':'惊蛰'
              },
              {
                'img':img4,
                'solarImg':solar4,
                'solarName':'春分'
              },
              {
                'img':img5,
                'solarImg':solar5,
                'solarName':'清明'
              },
              {
                'img':img6,
                'solarImg':solar6,
                'solarName':'谷雨'
              },
              {
                'img':img7,
                'solarImg':solar7,
                'solarName':'立夏'
              },
              {
                'img':img8,
                'solarImg':solar8,
                'solarName':'小满'
              },
              {
                'img':img9,
                'solarImg':solar9,
                'solarName':'芒种'
              },
              {
                'img':img10,
                'solarImg':solar10,
                'solarName':'夏至'
              },
              {
                'img':img11,
                'solarImg':solar11,
                'solarName':'小暑'
              },
              {
                'img':img12,
                'solarImg':solar12,
                'solarName':'大暑'
              },
              {
                'img':img13,
                'solarImg':solar13,
                'solarName':'立秋'
              },
              {
                'img':img14,
                'solarImg':solar14,
                'solarName':'处暑'
              },
              {
                'img':img15,
                'solarImg':solar15,
                'solarName':'白露'
              },
              {
                'img':img16,
                'solarImg':solar16,
                'solarName':'秋分'
              },
              {
                'img':img17,
                'solarImg':solar17,
                'solarName':'寒露'
              },
              {
                'img':img18,
                'solarImg':solar18,
                'solarName':'霜降'
              },
              {
                'img':img19,
                'solarImg':solar19,
                'solarName':'立冬'
              },
              {
                'img':img20,
                'solarImg':solar20,
                'solarName':'小雪'
              },
              {
                'img':img21,
                'solarImg':solar21,
                'solarName':'大雪'
              },
              {
                'img':img22,
                'solarImg':solar22,
                'solarName':'冬至'
              },
              {
                'img':img23,
                'solarImg':solar23,
                'solarName':'小寒'
              },
              {
                'img':img24,
                'solarImg':solar24,
                'solarName':'大寒'
              },
            ],
            slickOptions1: {
              slidesToShow: 1,
              slidesToScroll: 1,
              arrows: false,
              fade: true,
              asNavFor: '.slider-nav'
            },
            slickOptions2: {
              slidesToShow: 3,
              slidesToScroll: 1,
              asNavFor: '.slider-for',
              dots: false,
              centerMode: true,
              focusOnSelect: true,
              centerPadding: '60px',
              responsive: [
                    {
                      breakpoint: 768,
                      settings: {
                        arrows: true,
                        centerMode: true,
                        centerPadding: '10px',
                        slidesToShow: 3
                      }
                    },
                    {
                      breakpoint: 480,
                      settings: {
                        arrows: true,
                        centerMode: true,
                        centerPadding: '90px',
                        slidesToShow: 1
                      }
                    }
                  ]
            },
        };
    },

    // All slick methods can be used too, example here
    methods: {

    },
}

</script>

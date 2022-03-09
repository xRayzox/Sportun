<?php

use Twig\Environment;
use Twig\Error\LoaderError;
use Twig\Error\RuntimeError;
use Twig\Extension\SandboxExtension;
use Twig\Markup;
use Twig\Sandbox\SecurityError;
use Twig\Sandbox\SecurityNotAllowedTagError;
use Twig\Sandbox\SecurityNotAllowedFilterError;
use Twig\Sandbox\SecurityNotAllowedFunctionError;
use Twig\Source;
use Twig\Template;

/* base.html.twig */
class __TwigTemplate_4269a2333b79c1dd13c2fff27e6a4c43e384e92caa1d86b7fad1c59c2d0c34af extends Template
{
    private $source;
    private $macros = [];

    public function __construct(Environment $env)
    {
        parent::__construct($env);

        $this->source = $this->getSourceContext();

        $this->parent = false;

        $this->blocks = [
            'content' => [$this, 'block_content'],
        ];
    }

    protected function doDisplay(array $context, array $blocks = [])
    {
        $macros = $this->macros;
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02 = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->enter($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "base.html.twig"));

        // line 1
        echo "<!DOCTYPE html>
<html lang=\"zxx\">
<head>
\t<meta charset=\"UTF-8\">";
        // line 4
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(""), "html", null, true);
        echo "
\t<title>Fitmax</title>
\t<!-- =================== META =================== -->
\t<meta name=\"keywords\" content=\"\">
\t<meta name=\"description\" content=\"\">
\t<meta name=\"format-detection\" content=\"telephone=no\">
\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
\t<link rel=\"shortcut icon\" href=\"";
        // line 11
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/img/favicon.png"), "html", null, true);
        echo "\">
\t<!-- =================== STYLE =================== -->
\t<link rel=\"stylesheet\" href=\"";
        // line 13
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/css/slick.min.css"), "html", null, true);
        echo "\">
\t<link rel=\"stylesheet\" href=\"";
        // line 14
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/css/bootstrap-grid.css"), "html", null, true);
        echo "\">
\t<link rel=\"stylesheet\" href=\"";
        // line 15
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/css/font-awesome.min.css"), "html", null, true);
        echo "\">
\t<link rel=\"stylesheet\" href=\"";
        // line 16
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/css/style.css"), "html", null, true);
        echo "\">
</head>

<body id=\"home\">
\t<!--================ PRELOADER ================-->
\t<div class=\"preloader-cover\">
\t\t<div id=\"cube-loader\">
\t\t\t<div class=\"caption\">
\t\t\t\t<div class=\"cube-loader\">
\t\t\t\t\t<div class=\"cube loader-1\"></div>
\t\t\t\t\t<div class=\"cube loader-2\"></div>
\t\t\t\t\t<div class=\"cube loader-4\"></div>
\t\t\t\t\t<div class=\"cube loader-3\"></div>
\t\t\t\t</div>
\t\t\t</div>
\t\t</div>
\t</div>
\t<!--============== PRELOADER END ==============-->
\t
\t<!-- ================= HEADER ================= -->
\t<header class=\"header\">
\t\t<a href=\"#\" class=\"nav-btn\">
\t\t\t<span></span>
\t\t\t<span></span>
\t\t\t<span></span>
\t\t</a>
\t\t<div class=\"top-panel\">
\t\t\t<div class=\"container\">
\t\t\t\t<div class=\"header-left\">
\t\t\t\t\t<ul class=\"header-cont\">
\t\t\t\t\t\t<li><i class=\"fa fa-phone\" aria-hidden=\"true\"></i><a href=\"tel:18004886040\">1-800-488-6040</a></li>
\t\t\t\t\t\t<li><i class=\"fa fa-clock-o\" aria-hidden=\"true\"></i>Mon - Fri: 8:00AM - 7:00PM | Sat - Sun: Closed</li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t\t<div class=\"header-right\">
\t\t\t\t\t<form class=\"search-form\">
\t\t\t\t\t\t<input type=\"search\" class=\"search-form__field\" placeholder=\"Search\" value=\"\" name=\"s\">
\t\t\t\t\t\t<button type=\"submit\" class=\"search-form__submit\"><i class=\"fa fa-search\" aria-hidden=\"true\"></i></button>
\t\t\t\t\t</form>
\t\t\t\t\t<a href=\"about.html\">Sign in/registre</a>
\t\t\t\t</div>
\t\t\t</div>
\t\t</div>
\t\t<div class=\"header-menu\">
\t\t\t<div class=\"container\">
\t\t\t\t<div class=\"header-logo\">
\t\t\t\t\t<a href=\"index.html\" class=\"logo\"><img src=\"assets/img/logo.svg\" alt=\"logo\"></a>
\t\t\t\t</div>
\t\t\t\t<nav class=\"nav-menu\">
\t\t\t\t\t<ul class=\"nav-list\">
\t\t\t\t\t\t<li class=\"active\" ><a href=\"home.html\" >Home</a></li>
\t\t\t\t\t\t<li><a href=\"about.html\">About</a></li>
\t\t\t\t\t\t<li><a href=\"produits.html\">Produits</a></li>
\t\t\t\t\t\t<li><a href=\"services.html\">Services</a></li>\t
\t\t\t\t\t\t<li><a href=\"blog.html\">Blog</a></li>
\t\t\t\t\t\t<li><a href=\"contacts.html\">Contacts</a></li>
\t\t\t\t\t</ul>
\t\t\t\t</nav>
\t\t\t</div>
\t\t</div>
\t</header>
\t<!-- =============== HEADER END =============== -->









\t";
        // line 87
        $this->displayBlock('content', $context, $blocks);
        // line 89
        echo "







<!-- ================== FOOTER ================== -->

\t<!-- ================== FOOTER ================== -->

\t<!-- ================== FOOTER ================== -->
\t<footer>
\t\t<div class=\"container\">
\t\t\t<div class=\"row\">
\t\t\t\t<div class=\"col-sm-6 col-lg-3 footer-item-logo\">
\t\t\t\t\t<a href=\"index.html\" class=\"logo-footer\"><img src=\"assets/img/footer-logo2.svg\" alt=\"logo\"></a>
\t\t\t\t\t<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do</p>
\t\t\t\t\t<ul class=\"social-list\">
\t\t\t\t\t\t<li><a target=\"_blank\" href=\"https://www.facebook.com/rovadex\"><i class=\"fa fa-facebook\" aria-hidden=\"true\"></i></a></li>
\t\t\t\t\t\t<li><a target=\"_blank\" href=\"https://twitter.com/RovadexStudio\"><i class=\"fa fa-twitter\" aria-hidden=\"true\"></i></a></li>
\t\t\t\t\t\t<li><a target=\"_blank\" href=\"https://www.youtube.com\"><i class=\"fa fa-youtube\" aria-hidden=\"true\"></i></a></li>
\t\t\t\t\t\t<li><a target=\"_blank\" href=\"https://www.instagram.com/rovadex\"><i class=\"fa fa-instagram\" aria-hidden=\"true\"></i></a></li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t\t<div class=\"col-sm-6 col-lg-3 footer-item footer-item-list\">
\t\t\t\t\t<h3>Links</h3>
\t\t\t\t\t<ul class=\"footer-link\">
\t\t\t\t\t\t<li><a href=\"#\">Sed ut perspiciatis unde</a></li>
\t\t\t\t\t\t<li><a href=\"#\">Omnis iste natus error sit</a></li>
\t\t\t\t\t\t<li><a href=\"#\">Voluptatem accusantium</a></li>
\t\t\t\t\t\t<li><a href=\"#\">Doloremque laudantium</a></li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t\t<div class=\"col-sm-6 col-lg-3 footer-item\">
\t\t\t\t\t<h3>Contact us</h3>
\t\t\t\t\t<ul class=\"footer-cont\">
\t\t\t\t\t\t<li><i class=\"fa fa-phone\" aria-hidden=\"true\"></i><a href=\"tel:18004886040\">1-800-488-6040</a></li>
\t\t\t\t\t\t<li><i class=\"fa fa-envelope\" aria-hidden=\"true\"></i><a href=\"mailto:crossFit@gmail.com\">CrossFit@gmail.com</a></li>
\t\t\t\t\t\t<li><i class=\"fa fa-map-marker\" aria-hidden=\"true\"></i><a href=\"contacts.html\">London,Street 225r.21</a></li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t\t<div class=\"col-sm-6 col-lg-3 footer-item\">
\t\t\t\t\t<h3>Blog</h3>
\t\t\t\t\t<ul class=\"footer-blog\">
\t\t\t\t\t\t<li>
\t\t\t\t\t\t\t<a href=\"blog.html\" class=\"img-cover\"><img src=\"";
        // line 136
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/img/footer-icon-1.jpg"), "html", null, true);
        echo "\" alt=\"img\"></a>
\t\t\t\t\t\t\t<div class=\"footer-blog-info\">
\t\t\t\t\t\t\t\t<div class=\"name\"><a href=\"blog.html\">Sed ut perspiciatis</a></div>
\t\t\t\t\t\t\t\t<p>Omnis iste natus error sit voluptatem…</p>
\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t</li>
\t\t\t\t\t\t<li>
\t\t\t\t\t\t\t<a href=\"blog.html\" class=\"img-cover\"><img src=\"";
        // line 143
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/img/footer-icon-2.jpg"), "html", null, true);
        echo "\" alt=\"img\"></a>
\t\t\t\t\t\t\t<div class=\"footer-blog-info\">
\t\t\t\t\t\t\t\t<div class=\"name\"><a href=\"blog.html\">Sed ut perspiciatis</a></div>
\t\t\t\t\t\t\t\t<p>Omnis iste natus error sit voluptatem…</p>
\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t</li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t</div>
\t\t\t<div class=\"footer-bottom\">
\t\t\t\t<div class=\"copyright\"><a href=\"#\" target=\"_blank\">Rovadex</a> © 2019. Fitmax. All Rights Reserved.</div>
\t\t\t\t<ul class=\"footer-menu\">
\t\t\t\t\t<li class=\"active\"><a href=\"index.html\">Home</a></li>
\t\t\t\t\t<li><a href=\"about.html\">About</a></li>
\t\t\t\t\t<li><a href=\"about.html\">Produits</a></li>
\t\t\t\t\t<li><a href=\"services.html\">Services</a></li>
\t\t\t\t\t<li><a href=\"blog.html\">Blog</a></li>
\t\t\t\t\t<li><a href=\"contacts.html\">Contacts</a></li>
\t\t\t\t</ul>
\t\t\t</div>
\t\t</div>
\t</footer>
\t<!-- ================ FOOTER END ================ -->

\t<!--=================== TO TOP ===================-->
\t<a class=\"to-top\" href=\"#home\">
\t\t<i class=\"fa fa-chevron-up\" aria-hidden=\"true\"></i>
\t</a>
\t<!--================= TO TOP END =================-->

\t<!--=================== SCRIPT\t===================-->
\t<script src=\"";
        // line 174
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/js/jquery-2.2.4.min.js"), "html", null, true);
        echo "\"></script>
\t<script src=\"";
        // line 175
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/js/slick.min.js"), "html", null, true);
        echo "\"></script>
\t<script src=\"";
        // line 176
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/js/rx-lazy.js"), "html", null, true);
        echo "\"></script>
\t<script src=\"";
        // line 177
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/js/parallax.min.js"), "html", null, true);
        echo "\"></script>
\t<script src=\"";
        // line 178
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/js/scripts.js"), "html", null, true);
        echo "\"></script>
</body>
</html>";
        
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->leave($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof);

    }

    // line 87
    public function block_content($context, array $blocks = [])
    {
        $macros = $this->macros;
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02 = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->enter($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "content"));

        // line 88
        echo "\t";
        
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->leave($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof);

    }

    public function getTemplateName()
    {
        return "base.html.twig";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  275 => 88,  268 => 87,  258 => 178,  254 => 177,  250 => 176,  246 => 175,  242 => 174,  208 => 143,  198 => 136,  149 => 89,  147 => 87,  73 => 16,  69 => 15,  65 => 14,  61 => 13,  56 => 11,  46 => 4,  41 => 1,);
    }

    public function getSourceContext()
    {
        return new Source("<!DOCTYPE html>
<html lang=\"zxx\">
<head>
\t<meta charset=\"UTF-8\">{{asset('')}}
\t<title>Fitmax</title>
\t<!-- =================== META =================== -->
\t<meta name=\"keywords\" content=\"\">
\t<meta name=\"description\" content=\"\">
\t<meta name=\"format-detection\" content=\"telephone=no\">
\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
\t<link rel=\"shortcut icon\" href=\"{{asset('/Front/img/favicon.png')}}\">
\t<!-- =================== STYLE =================== -->
\t<link rel=\"stylesheet\" href=\"{{asset('/Front/css/slick.min.css')}}\">
\t<link rel=\"stylesheet\" href=\"{{asset('/Front/css/bootstrap-grid.css')}}\">
\t<link rel=\"stylesheet\" href=\"{{asset('/Front/css/font-awesome.min.css')}}\">
\t<link rel=\"stylesheet\" href=\"{{asset('/Front/css/style.css')}}\">
</head>

<body id=\"home\">
\t<!--================ PRELOADER ================-->
\t<div class=\"preloader-cover\">
\t\t<div id=\"cube-loader\">
\t\t\t<div class=\"caption\">
\t\t\t\t<div class=\"cube-loader\">
\t\t\t\t\t<div class=\"cube loader-1\"></div>
\t\t\t\t\t<div class=\"cube loader-2\"></div>
\t\t\t\t\t<div class=\"cube loader-4\"></div>
\t\t\t\t\t<div class=\"cube loader-3\"></div>
\t\t\t\t</div>
\t\t\t</div>
\t\t</div>
\t</div>
\t<!--============== PRELOADER END ==============-->
\t
\t<!-- ================= HEADER ================= -->
\t<header class=\"header\">
\t\t<a href=\"#\" class=\"nav-btn\">
\t\t\t<span></span>
\t\t\t<span></span>
\t\t\t<span></span>
\t\t</a>
\t\t<div class=\"top-panel\">
\t\t\t<div class=\"container\">
\t\t\t\t<div class=\"header-left\">
\t\t\t\t\t<ul class=\"header-cont\">
\t\t\t\t\t\t<li><i class=\"fa fa-phone\" aria-hidden=\"true\"></i><a href=\"tel:18004886040\">1-800-488-6040</a></li>
\t\t\t\t\t\t<li><i class=\"fa fa-clock-o\" aria-hidden=\"true\"></i>Mon - Fri: 8:00AM - 7:00PM | Sat - Sun: Closed</li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t\t<div class=\"header-right\">
\t\t\t\t\t<form class=\"search-form\">
\t\t\t\t\t\t<input type=\"search\" class=\"search-form__field\" placeholder=\"Search\" value=\"\" name=\"s\">
\t\t\t\t\t\t<button type=\"submit\" class=\"search-form__submit\"><i class=\"fa fa-search\" aria-hidden=\"true\"></i></button>
\t\t\t\t\t</form>
\t\t\t\t\t<a href=\"about.html\">Sign in/registre</a>
\t\t\t\t</div>
\t\t\t</div>
\t\t</div>
\t\t<div class=\"header-menu\">
\t\t\t<div class=\"container\">
\t\t\t\t<div class=\"header-logo\">
\t\t\t\t\t<a href=\"index.html\" class=\"logo\"><img src=\"assets/img/logo.svg\" alt=\"logo\"></a>
\t\t\t\t</div>
\t\t\t\t<nav class=\"nav-menu\">
\t\t\t\t\t<ul class=\"nav-list\">
\t\t\t\t\t\t<li class=\"active\" ><a href=\"home.html\" >Home</a></li>
\t\t\t\t\t\t<li><a href=\"about.html\">About</a></li>
\t\t\t\t\t\t<li><a href=\"produits.html\">Produits</a></li>
\t\t\t\t\t\t<li><a href=\"services.html\">Services</a></li>\t
\t\t\t\t\t\t<li><a href=\"blog.html\">Blog</a></li>
\t\t\t\t\t\t<li><a href=\"contacts.html\">Contacts</a></li>
\t\t\t\t\t</ul>
\t\t\t\t</nav>
\t\t\t</div>
\t\t</div>
\t</header>
\t<!-- =============== HEADER END =============== -->









\t{% block content %}
\t{% endblock %}








<!-- ================== FOOTER ================== -->

\t<!-- ================== FOOTER ================== -->

\t<!-- ================== FOOTER ================== -->
\t<footer>
\t\t<div class=\"container\">
\t\t\t<div class=\"row\">
\t\t\t\t<div class=\"col-sm-6 col-lg-3 footer-item-logo\">
\t\t\t\t\t<a href=\"index.html\" class=\"logo-footer\"><img src=\"assets/img/footer-logo2.svg\" alt=\"logo\"></a>
\t\t\t\t\t<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do</p>
\t\t\t\t\t<ul class=\"social-list\">
\t\t\t\t\t\t<li><a target=\"_blank\" href=\"https://www.facebook.com/rovadex\"><i class=\"fa fa-facebook\" aria-hidden=\"true\"></i></a></li>
\t\t\t\t\t\t<li><a target=\"_blank\" href=\"https://twitter.com/RovadexStudio\"><i class=\"fa fa-twitter\" aria-hidden=\"true\"></i></a></li>
\t\t\t\t\t\t<li><a target=\"_blank\" href=\"https://www.youtube.com\"><i class=\"fa fa-youtube\" aria-hidden=\"true\"></i></a></li>
\t\t\t\t\t\t<li><a target=\"_blank\" href=\"https://www.instagram.com/rovadex\"><i class=\"fa fa-instagram\" aria-hidden=\"true\"></i></a></li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t\t<div class=\"col-sm-6 col-lg-3 footer-item footer-item-list\">
\t\t\t\t\t<h3>Links</h3>
\t\t\t\t\t<ul class=\"footer-link\">
\t\t\t\t\t\t<li><a href=\"#\">Sed ut perspiciatis unde</a></li>
\t\t\t\t\t\t<li><a href=\"#\">Omnis iste natus error sit</a></li>
\t\t\t\t\t\t<li><a href=\"#\">Voluptatem accusantium</a></li>
\t\t\t\t\t\t<li><a href=\"#\">Doloremque laudantium</a></li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t\t<div class=\"col-sm-6 col-lg-3 footer-item\">
\t\t\t\t\t<h3>Contact us</h3>
\t\t\t\t\t<ul class=\"footer-cont\">
\t\t\t\t\t\t<li><i class=\"fa fa-phone\" aria-hidden=\"true\"></i><a href=\"tel:18004886040\">1-800-488-6040</a></li>
\t\t\t\t\t\t<li><i class=\"fa fa-envelope\" aria-hidden=\"true\"></i><a href=\"mailto:crossFit@gmail.com\">CrossFit@gmail.com</a></li>
\t\t\t\t\t\t<li><i class=\"fa fa-map-marker\" aria-hidden=\"true\"></i><a href=\"contacts.html\">London,Street 225r.21</a></li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t\t<div class=\"col-sm-6 col-lg-3 footer-item\">
\t\t\t\t\t<h3>Blog</h3>
\t\t\t\t\t<ul class=\"footer-blog\">
\t\t\t\t\t\t<li>
\t\t\t\t\t\t\t<a href=\"blog.html\" class=\"img-cover\"><img src=\"{{asset('/Front/img/footer-icon-1.jpg')}}\" alt=\"img\"></a>
\t\t\t\t\t\t\t<div class=\"footer-blog-info\">
\t\t\t\t\t\t\t\t<div class=\"name\"><a href=\"blog.html\">Sed ut perspiciatis</a></div>
\t\t\t\t\t\t\t\t<p>Omnis iste natus error sit voluptatem…</p>
\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t</li>
\t\t\t\t\t\t<li>
\t\t\t\t\t\t\t<a href=\"blog.html\" class=\"img-cover\"><img src=\"{{asset('/Front/img/footer-icon-2.jpg')}}\" alt=\"img\"></a>
\t\t\t\t\t\t\t<div class=\"footer-blog-info\">
\t\t\t\t\t\t\t\t<div class=\"name\"><a href=\"blog.html\">Sed ut perspiciatis</a></div>
\t\t\t\t\t\t\t\t<p>Omnis iste natus error sit voluptatem…</p>
\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t</li>
\t\t\t\t\t</ul>
\t\t\t\t</div>
\t\t\t</div>
\t\t\t<div class=\"footer-bottom\">
\t\t\t\t<div class=\"copyright\"><a href=\"#\" target=\"_blank\">Rovadex</a> © 2019. Fitmax. All Rights Reserved.</div>
\t\t\t\t<ul class=\"footer-menu\">
\t\t\t\t\t<li class=\"active\"><a href=\"index.html\">Home</a></li>
\t\t\t\t\t<li><a href=\"about.html\">About</a></li>
\t\t\t\t\t<li><a href=\"about.html\">Produits</a></li>
\t\t\t\t\t<li><a href=\"services.html\">Services</a></li>
\t\t\t\t\t<li><a href=\"blog.html\">Blog</a></li>
\t\t\t\t\t<li><a href=\"contacts.html\">Contacts</a></li>
\t\t\t\t</ul>
\t\t\t</div>
\t\t</div>
\t</footer>
\t<!-- ================ FOOTER END ================ -->

\t<!--=================== TO TOP ===================-->
\t<a class=\"to-top\" href=\"#home\">
\t\t<i class=\"fa fa-chevron-up\" aria-hidden=\"true\"></i>
\t</a>
\t<!--================= TO TOP END =================-->

\t<!--=================== SCRIPT\t===================-->
\t<script src=\"{{asset('/Front/js/jquery-2.2.4.min.js')}}\"></script>
\t<script src=\"{{asset('/Front/js/slick.min.js')}}\"></script>
\t<script src=\"{{asset('/Front/js/rx-lazy.js')}}\"></script>
\t<script src=\"{{asset('/Front/js/parallax.min.js')}}\"></script>
\t<script src=\"{{asset('/Front/js/scripts.js')}}\"></script>
</body>
</html>", "base.html.twig", "C:\\Users\\ASUS\\Desktop\\maram\\pidev\\templates\\base.html.twig");
    }
}

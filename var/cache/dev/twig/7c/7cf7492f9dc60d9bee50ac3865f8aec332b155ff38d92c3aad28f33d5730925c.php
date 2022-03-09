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

/* avis/new.html.twig */
class __TwigTemplate_630a62af6228b320271fd86580b92a9b7461f25b9acce9223f61bb6ce0a4c21d extends Template
{
    private $source;
    private $macros = [];

    public function __construct(Environment $env)
    {
        parent::__construct($env);

        $this->source = $this->getSourceContext();

        $this->blocks = [
            'content' => [$this, 'block_content'],
        ];
    }

    protected function doGetParent(array $context)
    {
        // line 1
        return "base.html.twig";
    }

    protected function doDisplay(array $context, array $blocks = [])
    {
        $macros = $this->macros;
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02 = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->enter($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "avis/new.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "avis/new.html.twig", 1);
        $this->parent->display($context, array_merge($this->blocks, $blocks));
        
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->leave($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof);

    }

    // line 2
    public function block_content($context, array $blocks = [])
    {
        $macros = $this->macros;
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02 = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->enter($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "content"));

        echo " ";
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(""), "html", null, true);
        echo "
\t<!-- ============ S-FITNESS-SLIDER ============ -->
\t<section class=\"s-fitness-slider\">
\t\t<div class=\"slider-navigation\">
\t\t\t<div class=\"container\">
\t\t\t\t<div class=\"slider-navigation-cover\"></div>
\t\t\t</div>
\t\t</div>
\t\t<div class=\"fitness-slider\">
\t\t\t<div class=\"fitness-slide\">
\t\t\t\t<div class=\"fitness-slider-effect\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<span class=\"scene-item\" data-depth=\"0.4\" style=\"'background-image: url(/Front/img/effect-1-1.svg);\"></span>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"slide-img-cover\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<img class=\"slide-img\" data-depth=\"0.2\" src=\"";
        // line 19
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/img/woman1.png"), "html", null, true);
        echo "\" alt=\"img\">
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"container\">
\t\t\t\t\t<img class=\"slide-img-effect\" src=\"";
        // line 23
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/img/slider-square.svg"), "html", null, true);
        echo "\" alt=\"img\">
\t\t\t\t\t<div class=\"text-bg\">workout</div>
\t\t\t\t\t<div class=\"fitness-slide-cover\">
\t\t\t\t\t\t<h2 class=\"title\">push <span>yourself</span></h2>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t</div>
\t\t\t<div class=\"fitness-slide\">
\t\t\t\t<div class=\"fitness-slider-effect\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<span class=\"scene-item\" data-depth=\"0.4\" style=\"background-image: url(/Front/img/effect-1-1.svg);\"></span>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"slide-img-cover\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<img class=\"slide-img\" data-depth=\"0.2\" src=\"";
        // line 38
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/img/woman2.png"), "html", null, true);
        echo "\" alt=\"img\">
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"container\">
\t\t\t\t\t<img class=\"slide-img-effect\" src=\"";
        // line 42
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/img/slider-square.svg"), "html", null, true);
        echo "\" alt=\"img\">
\t\t\t\t\t<div class=\"text-bg\">Yoga</div>
\t\t\t\t\t<div class=\"fitness-slide-cover\">
\t\t\t\t\t\t<h2 class=\"title\">push <span>yourself</span></h2>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t</div>
\t\t\t<div class=\"fitness-slide\">
\t\t\t\t<div class=\"fitness-slider-effect\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<span class=\"scene-item\" data-depth=\"0.4\" style=\"background-image: url(/Front/img/effect-1-1.svg);\"></span>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"slide-img-cover\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<img class=\"slide-img\" data-depth=\"0.2\" src=\"";
        // line 57
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/img/woman3.png"), "html", null, true);
        echo "\" alt=\"img\">
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"container\">
\t\t\t\t\t<img class=\"slide-img-effect\" src=\"";
        // line 61
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/Front/img/slider-square.svg"), "html", null, true);
        echo "\" alt=\"img\">
\t\t\t\t\t<div class=\"text-bg\">Cardio</div>
\t\t\t\t\t<div class=\"fitness-slide-cover\">
\t\t\t\t\t\t<h2 class=\"title\">push <span>yourself</span></h2>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t</div>
\t\t</div>
\t</section>
\t<!-- ========== S-FITNESS-SLIDER END ========== -->

        <center>
\t    ";
        // line 73
        echo twig_include($this->env, $context, "avis/_form.html.twig");
        echo "
        </center>



\t
";
        
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->leave($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof);

    }

    public function getTemplateName()
    {
        return "avis/new.html.twig";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  151 => 73,  136 => 61,  129 => 57,  111 => 42,  104 => 38,  86 => 23,  79 => 19,  52 => 2,  35 => 1,);
    }

    public function getSourceContext()
    {
        return new Source("{% extends 'base.html.twig' %}
{% block content %} {{asset('')}}
\t<!-- ============ S-FITNESS-SLIDER ============ -->
\t<section class=\"s-fitness-slider\">
\t\t<div class=\"slider-navigation\">
\t\t\t<div class=\"container\">
\t\t\t\t<div class=\"slider-navigation-cover\"></div>
\t\t\t</div>
\t\t</div>
\t\t<div class=\"fitness-slider\">
\t\t\t<div class=\"fitness-slide\">
\t\t\t\t<div class=\"fitness-slider-effect\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<span class=\"scene-item\" data-depth=\"0.4\" style=\"'background-image: url(/Front/img/effect-1-1.svg);\"></span>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"slide-img-cover\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<img class=\"slide-img\" data-depth=\"0.2\" src=\"{{asset('/Front/img/woman1.png')}}\" alt=\"img\">
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"container\">
\t\t\t\t\t<img class=\"slide-img-effect\" src=\"{{asset('/Front/img/slider-square.svg')}}\" alt=\"img\">
\t\t\t\t\t<div class=\"text-bg\">workout</div>
\t\t\t\t\t<div class=\"fitness-slide-cover\">
\t\t\t\t\t\t<h2 class=\"title\">push <span>yourself</span></h2>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t</div>
\t\t\t<div class=\"fitness-slide\">
\t\t\t\t<div class=\"fitness-slider-effect\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<span class=\"scene-item\" data-depth=\"0.4\" style=\"background-image: url(/Front/img/effect-1-1.svg);\"></span>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"slide-img-cover\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<img class=\"slide-img\" data-depth=\"0.2\" src=\"{{asset('/Front/img/woman2.png')}}\" alt=\"img\">
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"container\">
\t\t\t\t\t<img class=\"slide-img-effect\" src=\"{{asset('/Front/img/slider-square.svg')}}\" alt=\"img\">
\t\t\t\t\t<div class=\"text-bg\">Yoga</div>
\t\t\t\t\t<div class=\"fitness-slide-cover\">
\t\t\t\t\t\t<h2 class=\"title\">push <span>yourself</span></h2>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t</div>
\t\t\t<div class=\"fitness-slide\">
\t\t\t\t<div class=\"fitness-slider-effect\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<span class=\"scene-item\" data-depth=\"0.4\" style=\"background-image: url(/Front/img/effect-1-1.svg);\"></span>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"slide-img-cover\">
\t\t\t\t\t<div data-hover-only=\"true\" class=\"scene\">
\t\t\t\t\t\t<img class=\"slide-img\" data-depth=\"0.2\" src=\"{{asset('/Front/img/woman3.png')}}\" alt=\"img\">
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t\t<div class=\"container\">
\t\t\t\t\t<img class=\"slide-img-effect\" src=\"{{asset('/Front/img/slider-square.svg')}}\" alt=\"img\">
\t\t\t\t\t<div class=\"text-bg\">Cardio</div>
\t\t\t\t\t<div class=\"fitness-slide-cover\">
\t\t\t\t\t\t<h2 class=\"title\">push <span>yourself</span></h2>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t</div>
\t\t</div>
\t</section>
\t<!-- ========== S-FITNESS-SLIDER END ========== -->

        <center>
\t    {{ include('avis/_form.html.twig') }}
        </center>



\t
{% endblock %}", "avis/new.html.twig", "C:\\Users\\ASUS\\Desktop\\maram\\pidev\\templates\\avis\\new.html.twig");
    }
}

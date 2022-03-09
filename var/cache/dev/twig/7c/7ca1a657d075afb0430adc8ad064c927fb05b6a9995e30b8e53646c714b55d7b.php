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

/* produit/produit.html.twig */
class __TwigTemplate_6587eed84d657c896a8efe930e5d7897a7fee60ef895dd58f0b669dd2fe1c452 extends Template
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
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->enter($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "produit/produit.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "produit/produit.html.twig", 1);
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


\t<!-- =========== FITNESS-OUR-PROGRAM =========== -->
\t<section class=\"fitness-our-program\" style=\"background-image: url(/Front/img/bg-best.svg);\">
\t\t<span class=\"section-title-bg\">";
        // line 75
        echo "</span>
\t\t<div class=\"container\">
\t\t\t<h2 class=\"title-decor\">Our <span>Products</span></h2>
 \t\t\t\t";
        // line 78
        $context['_parent'] = $context;
        $context['_seq'] = twig_ensure_traversable((isset($context["produits"]) || array_key_exists("produits", $context) ? $context["produits"] : (function () { throw new RuntimeError('Variable "produits" does not exist.', 78, $this->source); })()));
        foreach ($context['_seq'] as $context["_key"] => $context["produit"]) {
            // line 79
            echo "\t\t\t<div class=\"row\">
\t\t\t\t<div class=\"col-sm-4 fitness-program-col\">
\t\t\t\t\t<div class=\"fitness-program-item\">
\t\t\t\t\t\t<div class=\"fitness-program-item-front\" style=\"background-image: url(";
            // line 82
            echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("picture/" . twig_get_attribute($this->env, $this->source, $context["produit"], "image", [], "any", false, false, false, 82))), "html", null, true);
            echo ");\">
\t\t\t\t\t\t\t<div class=\"fitness-program-item-inner\">
\t\t\t\t\t\t\t\t<div class=\"date\">Name : ";
            // line 84
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["produit"], "name", [], "any", false, false, false, 84), "html", null, true);
            echo " <br>";
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["produit"], "prix", [], "any", false, false, false, 84), "html", null, true);
            echo " DT<br>Click to  comment</a> </div>
\t\t\t\t\t\t\t\t<h3>";
            // line 85
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["produit"], "prix", [], "any", false, false, false, 85), "html", null, true);
            echo " DT</h3>
\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t</div>
\t\t\t\t\t\t<div class=\"fitness-program-item-back\" style=\"background-image: url(";
            // line 88
            echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("picture/" . twig_get_attribute($this->env, $this->source, $context["produit"], "image", [], "any", false, false, false, 88))), "html", null, true);
            echo ");\">
\t\t\t\t\t\t\t<a href=\"";
            // line 89
            echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("avis_new", ["id" => twig_get_attribute($this->env, $this->source, $context["produit"], "id", [], "any", false, false, false, 89)]), "html", null, true);
            echo "\" class=\"fitness-program-item-inner\">
\t\t\t\t\t\t\t\t<div class=\"date\">Quantity : ";
            // line 90
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["produit"], "quantity", [], "any", false, false, false, 90), "html", null, true);
            echo "</div>
\t\t\t\t\t\t\t\t<h3>Type : ";
            // line 91
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["produit"], "type", [], "any", false, false, false, 91), "html", null, true);
            echo "</h3>
\t\t\t\t\t\t\t</a>
\t\t\t\t\t\t</div>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t</div>
\t\t\t";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_iterated'], $context['_key'], $context['produit'], $context['_parent'], $context['loop']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 98
        echo "
\t\t</div>
\t</section>
\t<!-- ========= FITNESS-OUR-PROGRAM END ========= -->



\t
";
        
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->leave($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof);

    }

    public function getTemplateName()
    {
        return "produit/produit.html.twig";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  209 => 98,  196 => 91,  192 => 90,  188 => 89,  184 => 88,  178 => 85,  172 => 84,  167 => 82,  162 => 79,  158 => 78,  153 => 75,  136 => 61,  129 => 57,  111 => 42,  104 => 38,  86 => 23,  79 => 19,  52 => 2,  35 => 1,);
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


\t<!-- =========== FITNESS-OUR-PROGRAM =========== -->
\t<section class=\"fitness-our-program\" style=\"background-image: url(/Front/img/bg-best.svg);\">
\t\t<span class=\"section-title-bg\">{#Our Programsesmmmmm #}</span>
\t\t<div class=\"container\">
\t\t\t<h2 class=\"title-decor\">Our <span>Products</span></h2>
 \t\t\t\t{% for produit in produits %}
\t\t\t<div class=\"row\">
\t\t\t\t<div class=\"col-sm-4 fitness-program-col\">
\t\t\t\t\t<div class=\"fitness-program-item\">
\t\t\t\t\t\t<div class=\"fitness-program-item-front\" style=\"background-image: url({{ asset('picture/' ~ produit.image) }});\">
\t\t\t\t\t\t\t<div class=\"fitness-program-item-inner\">
\t\t\t\t\t\t\t\t<div class=\"date\">Name : {{ produit.name }} <br>{{ produit.prix }} DT<br>Click to  comment</a> </div>
\t\t\t\t\t\t\t\t<h3>{{ produit.prix }} DT</h3>
\t\t\t\t\t\t\t</div>
\t\t\t\t\t\t</div>
\t\t\t\t\t\t<div class=\"fitness-program-item-back\" style=\"background-image: url({{ asset('picture/' ~ produit.image) }});\">
\t\t\t\t\t\t\t<a href=\"{{ path('avis_new', {'id': produit.id}) }}\" class=\"fitness-program-item-inner\">
\t\t\t\t\t\t\t\t<div class=\"date\">Quantity : {{ produit.quantity }}</div>
\t\t\t\t\t\t\t\t<h3>Type : {{ produit.type }}</h3>
\t\t\t\t\t\t\t</a>
\t\t\t\t\t\t</div>
\t\t\t\t\t</div>
\t\t\t\t</div>
\t\t\t</div>
\t\t\t{% endfor %}

\t\t</div>
\t</section>
\t<!-- ========= FITNESS-OUR-PROGRAM END ========= -->



\t
{% endblock %}", "produit/produit.html.twig", "C:\\Users\\ASUS\\Desktop\\maram\\pidev\\templates\\produit\\Produit.html.twig");
    }
}

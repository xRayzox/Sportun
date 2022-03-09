<?php

namespace App\Form;

use App\Entity\Fournisseur;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
class FournisseurType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('name', TextType::class, array(
                'label' => 'name ',
                'attr' => array(
                    'placeholder' => 'name'
                )
            )
            )
            ->add('description', TextareaType::class, array(
                'label' => 'description ',
                'attr' => array(
                    'placeholder' => 'description'
                )
            )
            )
            ->add('email', TextType::class, array(
                'label' => 'email ',
                'attr' => array(
                    'placeholder' => 'email'
                )
            )
            )
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Fournisseur::class,
        ]);
    }
}

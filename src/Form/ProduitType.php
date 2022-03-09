<?php

namespace App\Form;

use App\Entity\Produit;
use App\Entity\Fournisseur;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
class ProduitType extends AbstractType
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
            ->add('prix', NumberType::class, array(
                'label' => 'prix ',
                'attr' => array(
                    'placeholder' => 'prix'
                )
            )
            )
            ->add('quantity', NumberType::class, array(
                'label' => 'quantity ',
                'attr' => array(
                    'placeholder' => 'quantity'
                )
            )
            )
            ->add('type', TextType::class, array(
                'label' => 'type ',
                'attr' => array(
                    'placeholder' => 'type'
                )
            )
            )
            ->add('image',FileType::class,[
                'mapped' => false
            ])
            ->add('idfournisseur', EntityType::class, [
                'class' => Fournisseur::class,
                'choice_label' => 'name',
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Produit::class,
        ]);
    }
}

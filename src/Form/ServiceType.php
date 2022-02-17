<?php

namespace App\Form;

use App\Entity\Service;

use App\Repository\ServiceRepository;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\File;   
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

class ServiceType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
       
            ->add('name')
            ->add('Description')
            ->add('title')
            ->add('Type')
            ->add('Num_Tel')
            ->add('Image',FileType::class,
            ['label'=>false,
                'multiple'=>false,
                'mapped'=>false,
                'required'=>false])
            ->add('Location',LocationType::class)
            ->add('Ajouter',SubmitType::class,[
                'attr' => ['class' => 'btn btn-primary'],
            ])
                 ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
//            'data_class' => Service::class,
        ]);
    }
}

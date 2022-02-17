<?php

namespace App\Entity;


use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass="ServiceRepository"::class)
 */
class Service
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="name is required")
     */
    private $name;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Description is required")
     */
    private $Description;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Assert\NotBlank(message="Title is required")
     */
    private $title;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Type is required")
     */
    private $Type;

    /**
     * @ORM\Column(type="integer")
     * @Assert\Length(min = 8, max =8, minMessage = "min_lenght", maxMessage = "max_lenght")
     * @Assert\Regex(pattern="/^[0-9]*$/", message="number_only") 
     * @Assert\NotBlank(message="num is required")
     */
    public $Num_Tel;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Image;

    /**
     * @ORM\OneToOne(targetEntity=Location::class, cascade={"persist", "remove"})
    * @Assert\Valid
     */
    private $Location;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(string $Description): self
    {
        $this->Description = $Description;

        return $this;
    }

    public function getTitle(): ?string
    {
        return $this->title;
    }

    public function setTitle(string $title): self
    {
        $this->title = $title;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->Type;
    }

    public function setType(string $Type): self
    {
        $this->Type = $Type;

        return $this;
    }

    public function getNumTel(): ?int
    {
        return $this->Num_Tel;
    }

    public function setNumTel(int $Num_Tel): self
    {
        $this->Num_Tel = $Num_Tel;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->Image;
    }

    public function setImage(string $Image): self
    {
        $this->Image = $Image;

        return $this;
    }

    public function getLocation(): ?Location
    {
        return $this->Location;
    }

    public function setLocation(?Location $Location): self
    {
        $this->Location = $Location;

        return $this;
    }
}

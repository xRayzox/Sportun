<?php

namespace App\Entity;


use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=LocationRepository::class)
 */
class Location
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     
     */
    private $id;

    /**
     * @ORM\Column(type="float")
     * @Assert\NotBlank(message="lat is required")
     */
    private $lat;

    /**
     * @ORM\Column(type="float")
     * @Assert\NotBlank(message="lng is required")
     */
    private $lng;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="region is required")
     */
    private $region;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getLat(): ?float
    {
        return $this->lat;
    }

    public function setLat(float $lat): self
    {
        $this->lat = $lat;

        return $this;
    }

    public function getLng(): ?float
    {
        return $this->lng;
    }

    public function setLng(float $lng): self
    {
        $this->lng = $lng;

        return $this;
    }

    public function getRegion(): ?string
    {
        return $this->region;
    }

    public function setRegion(string $region): self
    {
        $this->region = $region;

        return $this;
    }
}

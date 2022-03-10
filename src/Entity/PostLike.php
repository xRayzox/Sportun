<?php

namespace App\Entity;

use App\Repository\PostLikeRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PostLikeRepository::class)
 */
class PostLike
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $user;

    /**
     * @ORM\ManyToOne(targetEntity=Article::class, inversedBy="likes")
     */
    private $article;

    public function getId(): ?int
    {
        return $this->id;
    }


    /**
     *Permert de savoir si cet publication est like par se utilisateur oui ou non si non matafichich jaime zar9a
     * @param User $user
     * @return boolean
     */

    public function JaimePar(User $user):bool{


        foreach ($this->postLikes as $like){
            if($like->getUser()===$user)return true;
        }
        return false;


    }

    public function getArticle(): ?Article
    {
        return $this->article;
    }

    public function setArticle(?Article $article): self
    {
        $this->article = $article;

        return $this;
    }

}

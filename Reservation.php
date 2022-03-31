<?php

namespace App\Entity;

use App\Repository\ReservationRepository;
use Doctrine\Common\Collections\ArrayCollection;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * @ORM\Entity(repositoryClass=ReservationRepository::class)
 */
class Reservation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     *  @Groups("post:read")
     */
    private $id;

    /**
     * @ORM\Column(type="datetime")
     * @Groups("post:read")
     */
    private $dateDebut;

    /**
     * @ORM\Column(type="datetime")
     * @Groups("post:read")
     */
    private $dateFin;


    

    /**
     * @ORM\ManyToOne(targetEntity=Hebergement::class, inversedBy="reservations")
     * @Groups("post:read")
     */
    private $hebergement;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="reservations")
     * @Groups("post:read")
     */
    private $user;
    


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->dateDebut;
    }

    public function setDateDebut(\DateTimeInterface $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->dateFin;
    }

    public function setDateFin(\DateTimeInterface $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    

    public function getHebergement(): ?Hebergement
    {
        return $this->hebergement;
    }

    public function setHebergement(?Hebergement $hebergement): self
    {
        $this->hebergement = $hebergement;

        return $this;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }
    
}


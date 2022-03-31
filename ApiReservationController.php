<?php

namespace App\Controller;

use App\Entity\Reservation;
use App\Entity\Hebergement;
use App\Entity\User;
use App\Repository\ReservationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Validator\Constraints\Json;
class ApiReservationController extends AbstractController
{
 /**
     * @Route("/AfficherReservationsMobile", name="AfficherReservationsMobile")
     */
    public function AfficherReservationsMobile(Request $request)
    {
        $idUser = $request->get("user");
        $em = $this->getDoctrine()->getManager();
        $reservation = $em->getRepository(Reservation::class)->findBy(["user" => $em->getRepository(User::class)->find($idUser)]);

        return $this->json($reservation,200,[],['groups'=>'post:read']);

        //http://127.0.0.1:8000/AfficherReservationsMobile?user=9

    }

    /**
     * @Route("/SupprimerReservationMobile", name="SupprimerReservationMobile")
     */
    public function SupprimerReservationMobile(Request $request) {
        $id = $request->get("id");
        $em = $this->getDoctrine()->getManager();
        $reservation = $em->getRepository(Reservation::class)->find($id);
        if($reservation!=null ) {
            $em->remove($reservation);
            $em->flush();

            return new JsonResponse("reservation Supprime!", 200);
        }
        return new JsonResponse("ID reservation Invalide.");


    }

    /**
     * @Route("/AjouterReservationMobile", name="AjouterReservationMobile")
     */
    public function AjouterReservationMobile(Request $request)
    {
        $reservation = new Reservation();

        $idUser = $request->get("user");
        $idHebergement = $request->get("hebergement");

        $reservation->setUser($this->getDoctrine()->getManager()->getRepository(User::class)->find($idUser));
        $reservation->setHebergement($this->getDoctrine()->getManager()->getRepository(Hebergement::class)->find($idHebergement));
      $dateDebut= $request->get("dateDebut");
      $dateFin= $request->get("dateFin");

    $dateDebutConverted= \DateTime::createFromFormat('Y-m-d', $dateDebut);
    $dateFinConverted= \DateTime::createFromFormat('Y-m-d', $dateFin);
    $reservation->setDateDebut($dateDebutConverted);
    $reservation->setDateFin($dateFinConverted);

        try {
            $em = $this->getDoctrine()->getManager();
            $em->persist($reservation);
            $em->flush();

            return new JsonResponse("reservation Ajoute!", 200);
        }
        catch (\Exception $ex)
        {
            return new Response("Execption: ".$ex->getMessage());
        }

        //http://127.0.0.1:8000/AjouterReservationMobile?user=9&hebergement=6&dateDebut=5&dateFin=bouzid


    }

    /**
     * @Route("/ModifierReservationMobile", name="ModifierReservationMobile")
     */
    public function ModifierReservationMobile(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $reservation = $this->getDoctrine()->getManager()
            ->getRepository(Reservation::class)
            ->find($request->get("id"));

        $idUser = $request->get("user");
        $idHebergement = $request->get("hebergement");

        $reservation->setUser($this->getDoctrine()->getManager()->getRepository(User::class)->find($idUser));
        $reservation->setHebergement($this->getDoctrine()->getManager()->getRepository(Hebergement::class)->find($idHebergement));
        $dateDebut= $request->get("dateDebut");
        $dateFin= $request->get("dateFin");
  
      $dateDebutConverted= \DateTime::createFromFormat('Y-m-d', $dateDebut);
      $dateFinConverted= \DateTime::createFromFormat('Y-m-d', $dateFin);
      $reservation->setDateDebut($dateDebutConverted);
      $reservation->setDateFin($dateFinConverted);

        try {
            $em->persist($reservation);
            $em->flush();

            return new JsonResponse("Reservation Modifie!", 200);
        }
        catch (\Exception $ex)
        {
            return new Response("Execption: ".$ex->getMessage());
        }

        //http://127.0.0.1:8000/ModifierReservationMobile?id=8&user=9&hebergement=6&dateDebut=10&dateFin=ariana

    }

   







}

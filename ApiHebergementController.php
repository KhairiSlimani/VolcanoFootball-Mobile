<?php

namespace App\Controller;
use App\Repository\HebergementRepository;
use App\Entity\Hebergement;
use App\Entity\Agence;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class ApiHebergementController extends AbstractController
{
  /**
     * @Route("/AfficherHebergementsMobile", name="AfficherHebergementsMobile")
     */
    public function AfficherHebergementsMobile(Request $request)
    {
        $idAgence = $request->get("agence");
        $em = $this->getDoctrine()->getManager();
        $hebergement = $em->getRepository(Hebergement::class)->findBy(["agence" => $em->getRepository(Agence::class)->find($idAgence)]);

        return $this->json($hebergement,200,[],['groups'=>'post:read']);

        //http://127.0.0.1:8000/AfficherHebergementsMobile?agence=1

    }

}

<?php

namespace App\Repository;

use App\Entity\Service;

use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use Doctrine\ORM\EntityRepository;

/**
 * @method Service|null find($id, $lockMode = null, $lockVersion = null)
 * @method Service|null findOneBy(array $criteria, array $orderBy = null)
 * @method Service[]    findAll()
 * @method Service[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */

class ServiceRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Service::class);
    }

  

    // /**
    //  * @return Service[] Returns an array of Service objects
    //  */
    
    public function locall()
    {
        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery('SELECT s , L FROM APP\Entity\Service s
            JOIN s.Location L ');
            return $query->getResult();
    }

   /* public function findByExampleField($value)
    {
        return $this->createQueryBuilder('s')
            ->andWhere('s.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('s.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    

    
    public function findOneBySomeField(): ?Service
    {
        return $this->createQueryBuilder('s')
            ->join('S.id_location', 'i')
            
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }*/

  
  
}

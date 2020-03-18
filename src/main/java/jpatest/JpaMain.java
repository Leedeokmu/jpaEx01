package jpatest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setUsername("memberA");
            memberA.setAge(10);
            memberA.changeTeam(teamA);
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setUsername("memberB");
            memberB.setAge(20);
            memberB.changeTeam(teamA);
            em.persist(memberB);

            Member memberC = new Member();
            memberC.setUsername("memberC");
            memberC.setAge(20);
            memberC.changeTeam(teamB);
            em.persist(memberC);

            em.flush();
            em.clear();

            String query = "select distinct t from Team t join fetch t.members";
            List<Member> result = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "memberC")
                    .getResultList();
            for (Member s : result) {
                System.out.println("s : " + s.toString());
//                System.out.println("s : " + s.getName() + " | members : " + s.getMembers().size());
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}


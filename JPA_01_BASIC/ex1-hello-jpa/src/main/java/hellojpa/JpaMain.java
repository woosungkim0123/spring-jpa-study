package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 데이터베이스 커넥션 하나 받았다고 생각하면 쉬움

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        try {
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("김우성");
//            em.persist(member);

            List<Member> result = em.createQuery("select m from Member as m")
                    .setFirstResult(1) // 1번부터
                    .setMaxResults(8) // 8개 가져와
                    .getResultList();
            System.out.println("result = " + result);
            tx.commit();
            em.close();

        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

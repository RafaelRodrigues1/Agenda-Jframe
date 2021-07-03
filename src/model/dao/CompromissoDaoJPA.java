package model.dao;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.classes.Compromisso;

public class CompromissoDaoJPA {

    private static EntityManagerFactory factory;
    private final EntityManager entity;

    static {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("agenda");
        }
    }

    public CompromissoDaoJPA() {
        entity = factory.createEntityManager();
    }

    public CompromissoDaoJPA abrirTransaction() {
        entity.getTransaction().begin();
        return this;
    }

    public CompromissoDaoJPA commitarTransaction() {
        entity.getTransaction().commit();
        return this;
    }

    public void fechar() {
        entity.close();
    }

    public CompromissoDaoJPA salvar(Compromisso compromisso) {
        entity.persist(compromisso);
        return this;
    }

    public CompromissoDaoJPA alterar(Compromisso compromisso) {
        try {
            Compromisso compromissoUpd = buscarPorId(compromisso.getId());
            if (compromissoUpd == null) {
                return this;
            }
            entity.merge(compromisso);
            return this;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public CompromissoDaoJPA apagar(Compromisso compromisso) {
        try {
            Compromisso compromissoDel = buscarPorId(compromisso.getId());
            if (compromissoDel == null) {
                return this;
            }
            entity.remove(compromissoDel);
            return this;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Compromisso buscarPorId(Integer id) {
        return entity.find(Compromisso.class, id);
    }

    public List<Compromisso> buscar(LocalDate data) {
        try {
            String jpql = "SELECT c FROM Compromisso c where c.data = :data";

            return entity.createQuery(jpql, Compromisso.class).setParameter("data", data).getResultList();
        } finally {
            fechar();
        }
    }

    public List<Compromisso> buscar() {
        try {
            String jpql = "SELECT c FROM Compromisso c";

            return entity.createQuery(jpql, Compromisso.class).getResultList();
        } finally {
            fechar();
        }
    }

}

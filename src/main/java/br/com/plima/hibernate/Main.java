
package br.com.plima.hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String args[]) {

        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        //necessário pra acessar as funções de persistencia
        EntityManager em = entityManagerFactory.createEntityManager();
        //inserir dados
        try {
            buscarId();
        } catch (Exception e) {
            System.out.println("UPDATE: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void buscarId() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        //necessário pra acessar as funções de persistencia
        EntityManager em = entityManagerFactory.createEntityManager();

        Lembrete lembretes = null;
        Scanner scanner = new Scanner(System.in);
        //BuscaporId
        try {
            System.out.println("informe a tarefa: ");
            Long x = Long.parseLong(scanner.nextLine().toString());
            System.out.println("LONG X: " + x);
            lembretes = em.find(Lembrete.class, x.longValue());
            System.out.println("ITEM:" + lembretes);
        } catch (Exception e) {
            em.getTransaction().rollback();

            System.out.println("buscar:" + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void listarTodos() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        //necessário pra acessar as funções de persistencia
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Lembrete> lembretes = null;
        try {
            lembretes = em.createQuery("from Lembrete").getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();

            System.out.println("INSERT:" + e.getMessage());
        } finally {
            em.close();
        }

        if (lembretes != null) {
            lembretes.forEach(System.out::println);
        } else {
            System.out.println("Bancovazio");
        }
    }

    public static void inserirDados() {
        Lembrete lembrete = new Lembrete();
        Scanner scanner = new Scanner(System.in);
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatejpa");
        //necessário pra acessar as funções de persistencia
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            System.out.println("informe a tarefa: ");
            lembrete.setTitulo(scanner.nextLine());
            System.out.println("Digite a descrição:");
            lembrete.setDescricao(scanner.nextLine());
            //começo da gravacao no bd
            em.getTransaction().begin();
            em.merge(lembrete);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("UPDATE: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}

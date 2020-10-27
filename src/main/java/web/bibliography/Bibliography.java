package web.bibliography;

import context.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import web.bibliography.dataflow.Book;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class Bibliography {
    private Logger logger = LoggerFactory.getLogger(Bibliography.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "")
    public Response addBook(@Context HttpServletRequest request) throws Exception{
        Session session = null;
        Transaction transaction = null;
        try {
            String author = request.getParameter("author");
            String bookName = request.getParameter("bookName");
            String publishingHouse = request.getParameter("publishingHouse");

            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Book book = findBookByBookName(session, bookName);
            if(null == book) {
                book = new Book();
            }
            book.setAuthor(author);
            book.setBookName(bookName);
            book.setPublishingHouse(publishingHouse);
            session.save(book);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            System.out.println("ERROR");
        } finally {
            if (null != transaction) {
                transaction.rollback();
            }
            if (null != session) {
                session.close();
            }
        }
        return Response.status(Response.Status.OK);
    }

    private Book findBookByBookName(Session session, String bookName) {
        Criteria criteria = session.createCriteria(Book.class);
        criteria.add(Restrictions.eq("bookName", bookName));
        return (Book) criteria.uniqueResult();

    }

}

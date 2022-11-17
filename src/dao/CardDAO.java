package dao;

import database.Database;
import model.Card;
import model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDAO implements DAO<Card>,DAOReadId<Card>{
    private Connection database ;

    public CardDAO() {
        database = Database.getInstance();
    }

    @Override
    public List<Card> getAll() {
        List<Card> cards = new ArrayList<>();
        try {
            PreparedStatement ps = database.prepareStatement("select * from cards");
            ResultSet output = ps.executeQuery();
            while (output.next()){
                Card card = new Card(output.getInt("id"),output.getString("card_number"),
                        output.getString("password"),output.getString("cvv2"),
                        output.getObject("expire_date", LocalDate.class));
                cards.add(card);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }

        return cards;
    }

    @Override
    public void save(Card card) {
        try {
            PreparedStatement ps = database.prepareStatement("insert into cards(id,card_number,password,cvv2,expire_date) " +
                    "values (null,?,?,?,?)");
            ps.setString(1,card.getCardNumber());
            ps.setString(2,card.getPassword());
            ps.setString(3,card.getCvv2());
            ps.setObject(4,card.getExpireDate());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }
    }

    @Override
    public void update(Card card) {
        try {
            PreparedStatement ps = database.prepareStatement("update cards set id=null,card_number=?,password=?,cvv2=?,expire_date=? " +
                    "where id = ?");
            ps.setString(1,card.getCardNumber());
            ps.setString(2,card.getPassword());
            ps.setString(3,card.getCvv2());
            ps.setObject(4,card.getExpireDate());
            ps.setInt(5,card.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }

    }

    @Override
    public void delete(Card card) {
        try {
            PreparedStatement ps = database.prepareStatement("delete from cards where id = ?");
            ps.setInt(1,card.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }

    }

    @Override
    public Optional<Card> getById(Integer id) {
        Card card = null;
        try {
            PreparedStatement ps = database.prepareStatement("select * from cards where account_id = ?");
            ResultSet output = ps.executeQuery();
            if (output.first()){
                 card = new Card(output.getInt("id"),output.getString("card_number"),
                        output.getString("password"),output.getString("cvv2"),
                        output.getObject("expire_date", LocalDate.class));
                
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Database.close();
        }
        return Optional.of(card);
    }
}

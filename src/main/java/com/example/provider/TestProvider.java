package com.example.provider;

import com.example.webapi.models.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

import lombok.extern.java.Log;

@ApplicationScoped
@Log
public class TestProvider {

  @Resource(name = "jdbc/PostgresDS")
  private DataSource pgsql;

  /**
   * This method performs a simple select from an example table containing an id and name.
   *
   * @return List of test models
   */
  public List<Test> findAll() {
    try (final Connection con = pgsql.getConnection()) {
      try (final PreparedStatement stmt = con.prepareStatement(
          "SELECT id, name FROM example LIMIT 10 OFFSET 0"
      )) {
        try (final ResultSet rs = stmt.executeQuery()) {
          final List<Test> tests = new ArrayList<>();

          while (rs.next()) {
            tests.add(new Test(
                rs.getInt("id"),
                rs.getString("name")
            ));
          }

          return tests;
        }
      } catch (final SQLException e) {
        log.severe("ERROR: " + e.getMessage());
      }
    } catch (final SQLException e) {
      log.severe("ERROR: " + e.getMessage());
    }

    return new ArrayList<Test>() {{
      add(new Test(1, "emp01"));
      add(new Test(2, "emp02"));
    }};
  }
}

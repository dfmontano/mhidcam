package com.mhidcamsa.admin.controllers;

import com.mhidcamsa.admin.config.DataSource;
import com.mhidcamsa.admin.models.Balanceado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class BalanceadoController {

    public static Connection conn;
    public static PreparedStatement pstm;
    public static ResultSet rs;


    public static void insertBalanceado(Balanceado balanceado) {


        try {

            BasicDataSource bds = DataSource.getInstance().getBds();
            conn = bds.getConnection();

            pstm = conn.prepareStatement("INSERT INTO" +
                    " balanceado(idbalanceado, marca, tipo, perc_prot, volumen, precio, liquido) " +
                    "VALUES(?,?,?,?,?,?,?)");

            pstm.setString(1, balanceado.getId());
            pstm.setString(2, balanceado.getMarca());
            pstm.setString(3, balanceado.getTipo());
            pstm.setInt(4, balanceado.getPerc_prot());
            pstm.setDouble(5, balanceado.getVolumen());
            pstm.setBigDecimal(6, balanceado.getPrecio());
            pstm.setBoolean(7, balanceado.isLiquido());

            pstm.execute();

            System.out.println("Data saved!");

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                if (pstm != null) {
                    pstm.close();
                    System.out.println("Closing statement...");
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Closing connection...");
                }
            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }

    public Object[][] getAllData() {


        int rows = 0;
        int cols = 0;

        try{

            BasicDataSource bds = DataSource.getInstance().getBds();
            conn = bds.getConnection();

            pstm = conn.prepareStatement("SELECT count(1) as total FROM balanceado ");
            rs = pstm.executeQuery();

            rs.next();

            rows = rs.getInt("total");

            if (rs != null){

                rs.close();

            }

        }
        catch (SQLException e){

            e.printStackTrace();

        }

        try {


            pstm = conn.prepareStatement("SELECT " +
                    " * " +
                    " FROM balanceado");

            rs = pstm.executeQuery();

            cols = rs.getMetaData().getColumnCount();


        } catch (SQLException e) {

            e.printStackTrace();

        }


        Object[][] data = new String[rows][cols];

        int i = 0;

        try {

            while (rs.next()) {

                data[i][0] = rs.getString("idbalanceado");
                data[i][1] = rs.getString("marca");
                data[i][2] = rs.getString("tipo");
                data[i][3] = rs.getString("perc_prot");
                data[i][4] = rs.getString("volumen");
                data[i][5] = rs.getString("precio");
                data[i][6] = rs.getString("liquido");

                i++;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                if (rs != null) {

                    rs.close();

                }
                if (conn != null) {

                    conn.close();

                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }


        return data;

    }


}
package TM01;
import TM01.Network.ConnectURL;
import javax.swing.*;

import Model.ResponseModel;

import org.json.JSONArray;
import org.json.JSONObject;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class Tabel {
    private JTextField Message;

    public JTextField getTextField1() {
        return Message;
    }

    public void setTextField1(JTextField textField1) {
        this.Message = textField1;
    }

    public JTextField getTextField2() {
        return Label;
    }

    public void setTextField2(JTextField textField2) {
        this.Label = textField2;
    }

    public JTextField getTextField3() {
        return Comment;
    }

    public void setTextField3(JTextField textField3) {
        this.Comment = textField3;
    }


    private JTextField Label;
    private JTextField Comment;
    private JLabel message;
    private JLabel status;
    private JButton send;
    private JButton back;

    public JPanel getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(JPanel RESPONSE) {
        this.RESPONSE = RESPONSE;
    }

    private javax.swing.JPanel RESPONSE;


    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI_Response");
        frame.setContentPane(new Tabel().RESPONSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setUndecorated(true);
    }

    public Tabel() {
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectURL koneksisaya = new ConnectURL();
                URL myAddress = koneksisaya.buildURL
                        ("https://harber.mimoapps.xyz/api/getaccess.php");
                String response = null;
                try {
                    response = koneksisaya.getResponseFromHTTpUrl(myAddress);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                System.out.println("respon anda" + response);

                JSONArray responseJSON = new JSONArray(response);
                ArrayList<ResponseModel> responseModel = new ArrayList<>();
                for (int i = 0; i < responseJSON.length(); i++) {
                    ResponseModel resModel = new ResponseModel();
                    JSONObject myJSONObject = responseJSON.getJSONObject(i);
                    resModel.setMsg(myJSONObject.getString("message"));
                    resModel.setStatus(myJSONObject.getString("status"));
                    resModel.setComment(myJSONObject.getString("comment"));
                    responseModel.add(resModel);

                }
                System.out.println("Response are: ");
                for (int index = 0; index < responseModel.size(); index++) {
                    Message.setText(responseModel.get(index).getMsg());
                    Label.setText(responseModel.get(index).getStatus());
                    Comment.setText(responseModel.get(index).getComment());
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

package com.adit.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JList jListMahasiswa;
    private JTextField textFieldNim;
    private JTextField textFieldNama;
    private JTextField textFieldIPK;
    private JButton ButtonInsertFirst;
    private JButton buttonClear;
    private JButton buttonDelete;
    private DefaultListModel defaultListModel = new DefaultListModel();
    private List<String> arrayListMahasiswa = new ArrayList<>();
    private LinkedList<Mahasiswa> listMahasiswa = new LinkedList<>();

    class Mahasiswa {
        private String nim;
        private String nama;
        private String ipk;

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getIpk() {
            return ipk;
        }

        public void setIpk(String ipk) {
            this.ipk = ipk;
        }
    }

    public MainScreen() {
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        ButtonInsertFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = textFieldNim.getText();
                String nama = textFieldNama.getText();
                String ipk = String.valueOf(Double.parseDouble(textFieldIPK.getText()));

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(nim);
                mahasiswa.setNama(nama);
                mahasiswa.setIpk(ipk);

                insertFirst(mahasiswa);
                clearForm();
                refreshDataModel();
            }
        });
        jListMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = jListMahasiswa.getSelectedIndex();

                Mahasiswa hasilSearch = search(arrayListMahasiswa.get(index));

                if (hasilSearch != null) {
                    textFieldNim.setText(hasilSearch.getNim());
                    textFieldNama.setText(hasilSearch.getNama());
                    textFieldIPK.setText(String.valueOf(hasilSearch.getIpk()));
                }
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int index = jListMahasiswa.getSelectedIndex();

            if (index<0)
                return;
            String nama = jListMahasiswa.getSelectedValue().toString();

            for (int i = 0; i< arrayListMahasiswa.size(); i++){
                if (arrayListMahasiswa.get(i).equals(nama)) {
                    arrayListMahasiswa.remove(i);

                    break;
                }
            }
            clearForm();
            fromListMahasiswaToDataModel();
            }

            private void fromListMahasiswaToDataModel() {
                List<String> listNamaMahasiswa = new ArrayList<>();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    listNamaMahasiswa.add(
                            arrayListMahasiswa.get(i)
                    );
                }
                refreshDataModel(listNamaMahasiswa);
            }
            private void refreshDataModel(List<String> list) {
                defaultListModel.clear();
                defaultListModel.addAll(list);
                jListMahasiswa.setModel(defaultListModel);
            }


        });

    }

    private Mahasiswa search(String nama) {
        for (Mahasiswa mahasiswa : listMahasiswa) {
            if (mahasiswa == null)
                break;

            if (mahasiswa.getNama().equals(nama))
                return mahasiswa;
        }

        return null;
    }

    private void insertFirst(Mahasiswa mahasiswa) {
        listMahasiswa.insertFirst(mahasiswa);
    }

    private void refreshDataModel() {
        arrayListMahasiswa.clear();

        for (Mahasiswa mahasiswa : listMahasiswa) {
            if (mahasiswa == null)
                break;

            arrayListMahasiswa.add(mahasiswa.getNama());
        }

        defaultListModel.clear();
        defaultListModel.addAll(arrayListMahasiswa);
        jListMahasiswa.setModel(defaultListModel);
    }

    private void clearForm() {
        textFieldIPK.setText("");
        textFieldNama.setText("");
        textFieldNim.setText("");
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
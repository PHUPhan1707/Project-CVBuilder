package MenuApp.form;

import MenuApp.component.Card;
import MenuApp.model.Model_Card;
import MenuApp.swing.ScrollBar;
import MenuApp.swing.WrapLayout;
import SystemCVBuilder.AddInfo;
import SystemCVBuilder.AddInfo2;
import SystemCVBuilder.AddInfo3;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Form_Home extends javax.swing.JPanel {

    public Form_Home() {
        initComponents();
        init();
    }

    private void init() {
        panel.setLayout(new WrapLayout(WrapLayout.LEADING));
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Tạo và thêm card 1
        Card card1 = new Card(new Model_Card(new ImageIcon(getClass().getResource("/MenuApp/icon/testing/cv1.jpg")), "Minimal Redish Template", "A minimal redish template\nthat can show your\ncreativity and enthusiasm\namong others"));
        panel.add(card1);

        // Thêm sự kiện cho card 1
        card1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Thực hiện hành động khi nhấp vào card 1
            }
        });

        // Tạo và thêm card 2
        Card card2 = new Card(new Model_Card(new ImageIcon(getClass().getResource("/MenuApp/icon/testing/cv2.jpg")),  "Minimal Bluish Template", "A minimal blueish template\nthat is suitable for\nmaking the readers\nfeel strongly immersed"));
        panel.add(card2);

        // Thêm sự kiện cho card 2
        card2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Thực hiện hành động khi nhấp vào card 2
                AddInfo2 addInfo2 = new AddInfo2();
                addInfo2.setVisible(true);
            }
        });

        // Tạo và thêm card 3
        Card card3 = new Card(new Model_Card(new ImageIcon(getClass().getResource("/MenuApp/icon/testing/cv3.jpg")), "Black & White Template", "A minimal black and white template\nthat can make\nreaders to feel more comfortable\nand easier to keep track"));
        panel.add(card3);


         card3.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 AddInfo3 addInfo3 = new AddInfo3();
                 addInfo3.setVisible(true);
             }
         });

        panel.revalidate();
        panel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(242, 242, 242));

        jScrollPane1.setBorder(null);

        panel.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents



    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;

}

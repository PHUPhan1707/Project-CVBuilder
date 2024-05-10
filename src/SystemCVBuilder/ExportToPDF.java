
package SystemCVBuilder;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class ExportToPDF {
    public static void exportDataToPDF(int userID) {
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cvs", "root", "123456");

            // Truy vấn dữ liệu của người dùng cụ thể dựa trên ID
            String sql = "SELECT * FROM cv_info WHERE ID=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Tạo tài liệu PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("user_" + userID + ".pdf"));
                document.open();

                // Tạo phần tiêu đề
                Paragraph title = new Paragraph("User Information", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                // Tạo phần thông tin cá nhân
                PdfPTable personalInfoTable = new PdfPTable(2);
                personalInfoTable.setWidthPercentage(100);
                personalInfoTable.setSpacingBefore(20f);
                personalInfoTable.setSpacingAfter(20f);

                addCell(personalInfoTable, "First Name:", resultSet.getString("first_name"));
                addCell(personalInfoTable, "Last Name:", resultSet.getString("last_name"));
                addCell(personalInfoTable, "Date of Birth:", resultSet.getString("date_of_birth"));
                addCell(personalInfoTable, "Address:", resultSet.getString("address"));
                addCell(personalInfoTable, "Post Code:", resultSet.getString("post_code"));
                addCell(personalInfoTable, "Nationality:", resultSet.getString("nationality"));
                addCell(personalInfoTable, "Email:", resultSet.getString("email"));

                document.add(personalInfoTable);

                // Tạo phần bằng cấp
                PdfPTable qualificationsTable = new PdfPTable(2);
                qualificationsTable.setWidthPercentage(100);
                qualificationsTable.setSpacingBefore(20f);
                qualificationsTable.setSpacingAfter(20f);

                addCell(qualificationsTable, "University:", resultSet.getString("university"));
                addCell(qualificationsTable, "Degree:", resultSet.getString("degree"));
                addCell(qualificationsTable, "Skill 1:", resultSet.getString("skill1"));
                addCell(qualificationsTable, "Skill 2 :", resultSet.getString("skill2"));
                addCell(qualificationsTable, "Skill 3:", resultSet.getString("skill3"));

                document.add(qualificationsTable);

                // Thêm hình ảnh
                Blob avatarBlob = resultSet.getBlob("avatar");
                if (avatarBlob != null) {
                    byte[] imageData = avatarBlob.getBytes(1, (int) avatarBlob.length());
                    try {
                        Image image = Image.getInstance(imageData);
                        image.scaleToFit(100, 100); // Đặt kích thước của ảnh
                        image.setAbsolutePosition(document.getPageSize().getWidth() - 150, document.getPageSize().getHeight() - 120); // Đặt vị trí của ảnh
                        image.setAlignment(Element.ALIGN_RIGHT | Element.ALIGN_TOP);
                        document.add(image);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                document.close();

                System.out.println("PDF file exported successfully for user ID: " + userID);
            } else {
                System.out.println("User with ID " + userID + " not found in the database.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức hỗ trợ để thêm dữ liệu vào ô trong bảng PDF
    private static void addCell(PdfPTable table, String title, String value) {
        PdfPCell cell1 = new PdfPCell(new Phrase(title, FontFactory.getFont(FontFactory.HELVETICA, 12)));
        PdfPCell cell2 = new PdfPCell(new Phrase(value, FontFactory.getFont(FontFactory.HELVETICA, 12)));
        cell1.setBorder(Rectangle.NO_BORDER);
        cell2.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell1);
        table.addCell(cell2);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package calificaciones;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author luisa
 */
public class Main extends javax.swing.JFrame {
    private String direccionArchivo;
    /**
     * Creates new form Main
     */
    public Main(String direccion) {
        initComponents();
        this.direccionArchivo=direccion;
        llenarTabla();
        llenarTablaAlumnos();
    }
    
    private void llenarTabla() {
        List<String[]> filas=new ArrayList<>();
        int columnas=0;
         
        //crea arreglo con las materias
        //convierte el archivo a arreglo
        try (BufferedReader scan=new BufferedReader(new FileReader(direccionArchivo))) {
            String linea;
            while((linea=scan.readLine())!=null) {
                String[] arr=linea.split(",");
                filas.add(arr);
                int unidades=arr.length-1;
                if(unidades>columnas) {
                    columnas=unidades;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Archivo de materias corrupto");
            return;
        }
        
        
        //cabecera
        String[] NumUnidad=new String[1+columnas];
        NumUnidad[0]= "Materia";
        for(int i=1;i<=columnas;i++) {
            NumUnidad[i]="U"+i;
        }
        
        DefaultTableModel base=new DefaultTableModel(NumUnidad,0);
//imprimes en la tabla
        for(String[] ArrFilas:filas) {
            String[] filaCompleta=new String[1+columnas];
            Arrays.fill(filaCompleta,"");

            for(int i=0;i<ArrFilas.length;i++) {
                filaCompleta[i]=ArrFilas[i];
            }
            base.addRow(filaCompleta);
        }
        //tbMaterias.setModel(base);
        tbMateriasEdit.setModel(base);
    }
 
    private void guardarTabla(String nombreMateria,String[] nuevaFila) {
        try(BufferedWriter escrito=new BufferedWriter(new FileWriter(direccionArchivo, true))) {
            StringBuilder linea=new StringBuilder(nombreMateria);
            for(int i=1;i<nuevaFila.length;i++) {
                if(!nuevaFila[i].isEmpty()) {
                    linea.append(",").append(nuevaFila[i]);
                }
            }
            escrito.newLine();
            escrito.write(linea.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Archivo de materias corrupto");
        }
    }
    private void guardarTablaCompleta() {
        try(BufferedWriter escrito=new BufferedWriter(new FileWriter(direccionArchivo))) {
            DefaultTableModel base=(DefaultTableModel) tbMateriasEdit.getModel();
            for(int i=0;i<base.getRowCount();i++) {
                StringBuilder linea=new StringBuilder();
                for(int j=0;j<base.getColumnCount();j++) {
                    Object calificacion=base.getValueAt(i, j);
                    if(calificacion!=null) {
                        linea.append(calificacion.toString());
                    }
                    if(j<base.getColumnCount()-1) {
                        linea.append(",");
                    }
                }
                escrito.write(linea.toString());
                escrito.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Archivo de materias corrupto");
        }
    }

    private void llenarTablaAlumnos() {
        DefaultTableModel base=new DefaultTableModel(new Object[]{"Números de Control","Carrera", "Semestre"}, 0);
        try(BufferedReader lector=new BufferedReader(new FileReader("src/Archivos/usuarios.txt"))) {
            String linea;
            while((linea=lector.readLine())!=null) {
                String[] partes=linea.split(",");
                if (partes.length>=4) {
                    base.addRow(new Object[]{partes[0],partes[2],partes[3]});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Archivo de usuarios corrupto");
        }
        tbAlumnos.setModel(base);
    }
    private void mostrarCalificaciones(String numeroControl) {
        File archivo=new File("src/Archivos/materias-"+numeroControl+".txt");
        
        System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());
    System.out.println("Existe: " + archivo.exists());
  
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(this,
                "El archivo de calificaciones no existe: " + archivo.getAbsolutePath(),
                "Archivo no encontrado", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        StringBuilder calificaciones=new StringBuilder();
        try(BufferedReader lector=new BufferedReader(new FileReader(archivo))) {
            String linea;
            while((linea=lector.readLine())!=null) {
                calificaciones.append(linea).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Archivo de usuario corrupto");
            return;
        }

        JOptionPane.showMessageDialog(this,
            calificaciones.toString(),
            "Calificaciones de "+numeroControl,
            JOptionPane.INFORMATION_MESSAGE);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbAlumnos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMateriasEdit = new javax.swing.JTable();
        btnAnadirCaptura = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAlumnosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbAlumnos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gestion de Alumnos", jPanel1);

        tbMateriasEdit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbMateriasEdit);

        btnAnadirCaptura.setText("Añadir captura");
        btnAnadirCaptura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirCapturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnadirCaptura, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAnadirCaptura)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Captura de Calificaciones", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnadirCapturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirCapturaActionPerformed
        String nombreAlumno=JOptionPane.showInputDialog(this,"Numero de Control:");
        String nombreMateria=JOptionPane.showInputDialog(this,"Materia:");
        String numUnidad=JOptionPane.showInputDialog(this,"Unidad:");
        String calificacion=JOptionPane.showInputDialog(this,"Calificación:");
        
        try {
            int unidad=Integer.parseInt(numUnidad);
            DefaultTableModel base=(DefaultTableModel)tbMateriasEdit.getModel();

            boolean materiaNueva=false;
            
            for(int i=0;i<base.getRowCount();i++) {
                if(base.getValueAt(i,0).equals(nombreMateria)) {
                    if(unidad>=base.getColumnCount()) {
                        while(base.getColumnCount()<=unidad) {
                            base.addColumn("U"+base.getColumnCount());
                        }
                    }
                    
                    base.setValueAt(String.valueOf(calificacion),i,unidad);
                    materiaNueva=true;
                    break;
                }
            }
            
            if(!materiaNueva) {
                int columna=base.getColumnCount();
                String[] nuevaFila=new String[columna];
                Arrays.fill(nuevaFila,"");
                nuevaFila[0]=nombreMateria;
                if(unidad<columna) {
                    nuevaFila[unidad]=String.valueOf(calificacion);
                } else {
                    while(base.getColumnCount()<=unidad) {
                        base.addColumn("Unidad "+base.getColumnCount());
                    }
                    nuevaFila=Arrays.copyOf(nuevaFila,base.getColumnCount());
                    nuevaFila[unidad]=String.valueOf(calificacion);
                }
                base.addRow(nuevaFila);
                
                guardarTabla(nombreMateria,nuevaFila);
            } else {
                guardarTablaCompleta();
            }

        } 
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Datos invalidos");
        }
    }//GEN-LAST:event_btnAnadirCapturaActionPerformed

    private void tbAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAlumnosMouseClicked
        if (tbAlumnos.getSelectedRow()!=-1) {
            String numeroControl=tbAlumnos.getValueAt(tbAlumnos.getSelectedRow(),0).toString();
            mostrarCalificaciones(numeroControl);
        }
    }//GEN-LAST:event_tbAlumnosMouseClicked
    
    /**
     * @param args the command line arguments
     */
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadirCaptura;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbAlumnos;
    private javax.swing.JTable tbMateriasEdit;
    // End of variables declaration//GEN-END:variables
}

package com.customFunctions;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class PaintApp extends JFrame implements MouseListener, MouseMotionListener{

    private Color canvasColor, penColor;
    private int pensize;
    String path;
  
    private float[] stroke={1.0f}; 

    private Color[] penColorList = {
        Color.black,
        Color.red,
        Color.green,
        Color.blue,
        Color.gray,
        Color.yellow,
        Color.orange,
        new Color(102,102,0)
    };

    private String[] penColorRef = {
        "Black",
        "Red",
        "Green",
        "Blue",
        "Gray",
        "Yellow",
        "Orange",
        "Dark Olive",
        "Erase",
        "Custom"
    };

    private String[] canvasColorRef = {
        "White",
        "Dark Olive",
        "Black",
        "Green",
        "Red",
        "Yellow",
        "Blue",
        "Cyan"
    };

    private Color[] canvasColorList = {
        Color.white,
        new Color(102,102,0),
        Color.black,
        Color.green,
        Color.red,
        Color.yellow,
        Color.blue,
        Color.cyan
    };

    private final int frame_width;
    private final int frame_height;
    private int curx;
    private int cury;
    private int color_index;
    private final int max_pensize;
    private int prevx;
    private int prevy;

    boolean shouldClear = true;
    boolean paint = true;
    Graphics g;
    private BufferedImage backImage;
    private BufferedImage previewImage;

    private BasicStroke penStroke;

    private JComponent canvas;
    private JComponent controls;
    private JComponent preview;
    private JColorChooser customColorChooser;

    private JSlider brushSizeSlider;
    private JComboBox penColorCombo;
    private JComboBox canvasColorCombo;

    private JLabel penLabel;
    private JLabel canvasLabel;
    private JButton clearCanvasButton;
    private JButton browseButton;

    private JMenuBar menuBar;
    private JMenuItem exitMenuItem;
    private JMenuItem saveMenuItem;
    @SuppressWarnings("unused")
	private JMenuItem browseMenuItem;
    private JMenu fileMenu;



    public PaintApp(){
        frame_width = 540;
        frame_height = 600;
       
        canvasColor = canvasColorList[0];
        penColor = penColorList[0];

        color_index=0;

        // Set maximum size for our brush.
        max_pensize = 30;
        pensize = max_pensize;

        penStroke = new BasicStroke(pensize,BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,10.0f,stroke,0.0f);

        backImage = new BufferedImage(frame_width,
                frame_height, BufferedImage.TYPE_INT_RGB);
        previewImage = new BufferedImage(max_pensize,
                max_pensize, BufferedImage.TYPE_INT_ARGB);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(UnsupportedLookAndFeelException e){}
        catch(IllegalAccessException e){}
        catch(ClassNotFoundException e){}
        catch(InstantiationException e){}

        setTitle("Paint Application");
        setVisible(true);
        setResizable(false);
             
        setSize(frame_width, frame_height);
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                closePaint();
            }
        });

        initGUIElements();
    }

    @SuppressWarnings("static-access")
    private void initGUIElements(){
                
        canvas = new MyCanvas();
        canvas.setVisible(true);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        getContentPane().add(canvas, BorderLayout.CENTER);
      
        customColorChooser = new JColorChooser();
        customColorChooser.getSelectionModel().addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                penColor = customColorChooser.getColor();
            }
        });

        controls = new JPanel();
       
  controls.setPreferredSize(new Dimension(250, 100));
        controls.setVisible(true);
        controls.setLayout(new FlowLayout());
        controls.setBorder(BorderFactory.createTitledBorder("Controls"));
       
        getContentPane().add(controls, BorderLayout.SOUTH);
      
     //   controls.setSize(20, 400);
        preview = new BrushSizeView();
        preview.setVisible(true);
        controls.add(preview);
      
        brushSizeSlider = new JSlider();
        brushSizeSlider.setMaximum(max_pensize);
        brushSizeSlider.setMinimum(1);
        brushSizeSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                setPenSize(brushSizeSlider.getValue());
                preview.repaint();
            }
        });
        controls.add(brushSizeSlider);

        penLabel = new JLabel("Choose Pen Color:");
        penLabel.setVisible(true);
        controls.add(penLabel);
        

        penColorCombo = new JComboBox();
        penColorCombo.setModel(new DefaultComboBoxModel(penColorRef));
        penColorCombo.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e){
                color_index = penColorCombo.getSelectedIndex();
                String col_str = penColorCombo.getSelectedItem().toString();
                if(col_str.equals("Erase")){
                    setPenColor(canvasColor); // Eraser Color = background Color.
                }
                else if(col_str.equals("Custom")){
                    Color temp = customColorChooser.showDialog(PaintApp.this,
                            "Choose custom color", penColor);
                    if(temp!=null){
                        customColorChooser.setColor(temp);
                        setPenColor(temp);
                    }
                }
                else{
                    setPenColor(penColorList[color_index]);
                }
            }
        });
        controls.add(penColorCombo);
        

        canvasLabel = new JLabel();
        canvasLabel.setText("Change canvas color:");
        canvasLabel.setVisible(true);
        controls.add(canvasLabel);

        canvasColorCombo = new JComboBox();
        canvasColorCombo.setModel(new DefaultComboBoxModel(canvasColorRef));
        canvasColorCombo.setVisible(true);
        controls.add(canvasColorCombo);
       
       
        browseButton = new JButton("Browse");
        browseButton.setVisible(true);
        browseButton.setToolTipText("import image from computer");
        browseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 Chooser frame = new Chooser();
                                //   field.setText(frame.fileName);
                                                path=frame.fileName;
                                                browseImage();
            }
        });
        browseButton.setMnemonic(KeyEvent.VK_W);
        controls.add(browseButton);
        
        
        clearCanvasButton = new JButton("Clear Canvas");
        clearCanvasButton.setVisible(true);
        clearCanvasButton.setToolTipText("Clear the Canvas with the specified color on the left");
        clearCanvasButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearCanvas();
            }
        });
        clearCanvasButton.setMnemonic(KeyEvent.VK_C);
        controls.add(clearCanvasButton);

        menuBar = new JMenuBar();
        
        setJMenuBar(menuBar);
     
      
        fileMenu = new JMenu("File");
        
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
      
     //   browseMenuItem=new JMenuItem("browse");
       // browseMenuItem.setMnemonic(KeyEvent.VK_B);
        saveMenuItem = new JMenuItem("Save ");
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
       /* 
        browseMenuItem.addActionListener(new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                 try{
                    
                     Chooser frame = new Chooser();
                                //   field.setText(frame.fileName);
                                                path=frame.fileName;
                                                browseImage();
                 }catch(IOException io){}
             }
                
        });*/
       
        
        saveMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    saveAs();
                }catch(IOException io){}
            }
        });
        fileMenu.add(saveMenuItem);

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                closePaint();
            }
        });
        exitMenuItem.setMnemonic(KeyEvent.VK_Q);
        fileMenu.add(exitMenuItem);
    }
    
   
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){
    }
    public void mouseMoved(MouseEvent e){
        paint = false;
    }
    public void mouseDragged(MouseEvent e){
        mouseClicked(e);
    }

    public void mouseClicked(MouseEvent e){
        curx = e.getX();
        cury = e.getY();
        if(!paint){
            prevx=curx;
            prevy=cury;
            paint = true;
        }
        canvas.paintImmediately(Math.min(prevx, curx) - pensize / 2 - 1,
                Math.min(prevy, cury) - pensize / 2 - 1,
                Math.abs(prevx - curx) + pensize + 2,
                Math.abs(prevy - cury) + pensize+ 2);
        prevx=curx;
        prevy=cury;
    }
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}

    public void setPenColor(Color pencolor){
        this.penColor = pencolor;
        preview.repaint();
        repaint();
    }


    public void clearCanvas(){
        canvasColor = canvasColorList[canvasColorCombo.getSelectedIndex()];
        shouldClear = true;
        canvas.repaint();
    }

    public void setPenSize(int pensize){
        this.pensize = pensize;
        penStroke = new BasicStroke(pensize,BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,10.0f,stroke,0.0f);
    }

    public void closePaint(){
        int choice = JOptionPane.showConfirmDialog(this, "Exit Application?",
                "Exit Paint Application", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
           // System.exit(0);
        }
        else{
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }
    public synchronized void browseImage() 
    {
                  try{
                                  System.out.println("Browsing for pic");
                                  System.out.flush();
                                 /* BufferedImage image=(BufferedImage) Jimi.getImage("painting.png");
                                  Image displayImage; 
                                
                                    displayImage =Toolkit.getDefaultToolkit().getImage("painting.png");
                                   Canvas.image=displayImage;
                                   Graphics graphics = Canvas.image.getGraphics();*/
                                   
                                    /*int answer = chooser.showOpenDialog(component);   
                                    if (answer == JFileChooser.APPROVE_OPTION)   
                                    {   
                                        File file = chooser.getSelectedFile();   
                                        textField.setText(file.getAbsolutePath());   
                                    }  
 */
                               //   JFileChooser chooser = new JFileChooser();
/*                              FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                       "JPG & GIF Images", "jpg", "gif");*/
                                 //  chooser.setFileFilter(filter);
               //                int returnVal = chooser.showOpenDialog(parent);
                               //    if(returnVal == JFileChooser.APPROVE_OPTION) {
                                //      System.out.println("You chose to open this file: " +
                                     //      chooser.getSelectedFile().getName();
                                //   }
                                 BufferedImage image = ImageIO.read(new File(path));
                                 //  canvas.backImage=image;
               //       canvas.paint(image);
                 //     Graphics g=image;
               //            canvas.image=image;
                               Graphics graphics=canvas.getGraphics();
                 //   Graphics g2d = image.getGraphics();
                   // graphics.drawImage(image, 0, 0, null);
                      MyCanvas a=new MyCanvas();
                     a.paintComponent1(graphics,image);
                     
                   //   shouldClear = false;
               //                 canvas.repaint();
                               //   browseButton.addActionListener(this);
                                //  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                                 
                   }
                  catch (Exception e){
                                  System.out.println("Picture browse failed: " + e.getMessage());
                  }
    }
    public void saveAs() throws IOException{
       /* JFileChooser save = new JFileChooser();
        if(save.showSaveDialog(this) == JFileChooser.APPROVE_OPTION
                && save.getSelectedFile()!=null){

            File f = save.getSelectedFile();
            if(!f.getPath().endsWith(".png")){
                f = new File("C:\\HandsOnTemp\\153878\\404052\\test\\hifi55" + ".png");
            }

            if(f.exists()){
                String overWriteMSG = "\""+f.getPath()+"\" already exists! Overwrite it?";
                if(JOptionPane.showConfirmDialog(this, overWriteMSG) != JOptionPane.OK_OPTION){
                    return ;
                }
            }
            else{
                if(!f.createNewFile()){
                    String failMSG = "Sorry could not create the specified file.";
                    JOptionPane.showMessageDialog(this, failMSG);
                    return ;
                }
            }*/
            createSave();
       // }
    }

    public void createSave(){
        BufferedImage temp_img = backImage;
        try{
        	String path=System.getProperty("user.home") + "\\Desktop";

            ImageIO.write(temp_img, "png" , new File(path+"\\greeting" + ".png"));
        }catch(IOException e){
            String errorMSG = "An error occured while trying to save the file!";
            JOptionPane.showMessageDialog(this, errorMSG,
                    "Failed", JOptionPane.ERROR_MESSAGE);
            return ;
        }
      //  String done = "File saved to "+f.getPath();
      //  JOptionPane.showMessageDialog(this, done,
        //        "Saved", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String args[]){
            SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                @SuppressWarnings("unused")
				PaintApp paint=new PaintApp();
            }
        });
    }
    
    

    // Inner class declaring the canvas attributes
    private class MyCanvas extends JComponent {
        public MyCanvas() {
        }

        // Main Piant and Update function
        @Override
        public void paintComponent(Graphics g) {
                
            super.paintComponent(g);
            Graphics2D g2 = backImage.createGraphics();
            if (shouldClear) {
                g2.setColor(canvasColor);
                g2.fillRect(0, 0, frame_width, frame_height);
                shouldClear = false;
            } else {
                g2.setStroke(penStroke);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(penColor);
                g2.drawLine(prevx, prevy, curx, cury);
            }
            g.drawImage(backImage, 0, 0, null);
        }
        public void paintComponent1(Graphics g,BufferedImage image) {
                 super.paintComponent(g);
             Graphics2D g2 = backImage.createGraphics();
             if (shouldClear) {
                 g2.setColor(canvasColor);
                 g2.fillRect(0, 0, frame_width, frame_height);
                 shouldClear = false;
             } else {
                 g2.setStroke(penStroke);
                 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
                 g2.setColor(penColor);
                 g2.drawLine(prevx, prevy, curx, cury);
             }
             backImage=image;
             g.drawImage(backImage, 0, 0, null);
                                }
    }
    class Chooser extends JFrame {

                JFileChooser chooser;
                String fileName;

                public Chooser() {
                chooser = new JFileChooser();
                int r = chooser.showOpenDialog(new JFrame());
                if (r == JFileChooser.APPROVE_OPTION) {
                fileName = chooser.getSelectedFile().getPath();
                }
                }
                }

    private class BrushSizeView extends JComponent{
        public BrushSizeView(){
        }

        @Override
        public boolean isOpaque(){
            return true;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = previewImage.createGraphics();
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);
            g2d.setColor(penColor);
         g2d.fillOval(getPreferredSize().width / 2 - pensize / 2,
                  getPreferredSize().height / 2 - pensize / 2, pensize, pensize);
            g.drawImage(previewImage, 0, 0, null);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(30, 30);
        }

        @Override
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }
    }
}

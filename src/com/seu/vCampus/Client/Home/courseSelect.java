package com.seu.vCampus.Client.Home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class courseSelect extends JPanel{


    /**
     * Launch the application.
     */


    /**
     * Create the application.
     */
    public courseSelect(){

        String[] termlist=new String[] {"第一学期","第二学期"};
        JComboBox term = new JComboBox(termlist) ;
        setLayout(new BorderLayout());
        add(term, BorderLayout.NORTH);
        JPanel panel = new JPanel();

        JLabel label = new JLabel("\u5DF2\u9009\u8BFE\u7A0B");
        panel.add(label);
        add(panel, BorderLayout.WEST);//已选课程列表

        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(430);


        JPanel coursepanel1 = new JPanel();//第一学期可选课程
        splitPane.setLeftComponent(coursepanel1);

        JPanel selectpanel1 = new JPanel();//第一学期选择按钮
        splitPane.setRightComponent(selectpanel1);

        JPanel coursepanel2 =new JPanel();//第二学期可选课程

        JPanel selectpanel2=new JPanel();//第二学期可选课程
        add(splitPane, BorderLayout.CENTER);

        term.addItemListener(new ItemListener(){//选择哪个学期
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED)
                {
                    if(term.getSelectedIndex()==0)
                    {
                        splitPane.setLeftComponent(coursepanel1);
                        splitPane.setRightComponent(selectpanel1);
                        splitPane.setDividerLocation(430);
                    }
                    else
                    {
                        splitPane.setLeftComponent(coursepanel2);
                        splitPane.setRightComponent(selectpanel2);
                        splitPane.setDividerLocation(430);
                    }
                }
            }

        });

    }


    /**
     * Initialize the contents of the frame.
     */

}

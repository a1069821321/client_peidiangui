package com.client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;

///
import com.client.modules.*;
import com.client.window.*;
import javafx.util.Callback;

import java.awt.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Main_window extends Application{
    public  static Stage main_stage;
    private Polyline drawline;
    private static String being = "";
    private double xs=20;
    private Line co_x;private Line co_y;private Line alli_bottom;private Line alli_top;
    private Polyline co_x_degree;private Polyline co_y_degree;
    private VBox firstarea;
    private VBox secondarea;
    private VBox conditionarea;
    public static Label tempmaxi;public static Label tempnowi;

    public static ImageView heat_picture;
    public static Socket client;
    private List<Integer> selected_ones = new ArrayList();
    private VBox figure_area_=new VBox();

   //大框架
    private VBox root = new VBox();
    private VBox top = new VBox();
    private HBox content = new HBox();

    private AnchorPane title = new AnchorPane();
    private Button close = new Button();
    private Button minis = new Button();
    private Button maxs = new Button();
    private  Button user = new Button();
    private  Button downtray = new Button();
    private Label logo = new Label();
    private Label tit = new Label("配电柜监测");

    //第一分区
    private VBox first_area = new VBox();
    private VBox machine_area = new VBox();
    private HBox model_area = new HBox();
    private VBox select_area = new VBox();
    private ObservableList<Data> list = FXCollections.observableArrayList();
    private ListView<Data> listview = new ListView<Data>();
    private Label selected_num =new Label();
    private HBox m_title_area = new HBox();
    private Label m_title = new Label("配电柜");
    private Button m1 = new Button("全局");
    private Button m2 = new Button("分立");
    private Label none1 = new Label("");
    private Label none2 = new Label("");
    private CheckBox all_cancel = new CheckBox("全选/全不选");
    private VBox function_area = new VBox();
    private  Group g_menu = new Group();
    private VBox function_area_a = new VBox();
    private VBox function_area_b = new VBox();
    private VBox function_area_c = new VBox();
    private VBox function_area_d = new VBox();
    private Button a1 =new Button("实时温度");
    private Button a2 =new Button("热力图");
    private Button a3 =new Button("均温图");
    private Button a4 =new Button("测试—温度");
    private Button a5 =new Button("温湿度简易图");
    private Button b1 =new Button("");
    private Button b2 =new Button("测试listview");
    private Button b3 =new Button("参数A");
    private Button b4 =new Button("参数A");
    private Button b5 =new Button("参数A");
    private Button c1 =new Button("参数A");
    private Button c2 =new Button("参数A");
    private Button c3 =new Button("参数A");
    private Button c4 =new Button("参数A");
    private Button c5 =new Button("参数A");
    private  Button d2 =new Button("重设");
    private Button d1 =new Button("概况图");
    private TitledPane t1 = new TitledPane("温湿度",function_area_a);
    private TitledPane t2 = new TitledPane("振动", function_area_b);
    private TitledPane t3 = new TitledPane("噪声", function_area_c);
    private TitledPane t4 = new TitledPane("数据流", function_area_d);
    private Accordion menu = new Accordion();
    private Label menu_title = new Label("功能");

    //第二分区
    private VBox second_area = new VBox();
    private VBox figure_area = new VBox();
    private  HBox figure_tools = new HBox();
    private Button removeall = new Button("清空");
    private Button stopall = new Button("停止");
    private VBox information_area = new VBox();
    private TextArea information = new TextArea();

    //第三分区
    private VBox third_area = new VBox();
    private VBox condition_area = new VBox();
    private VBox title1 = new VBox();
    private VBox title2 = new VBox();
    private VBox content1 = new VBox();
    private VBox content2 = new VBox();
    private Label title1l = new Label(""); //数据组一的标题
    private Label title2l = new Label("");
    private VBox condition_first = new VBox();
    private VBox condition_second = new VBox();
    private Label data1_t = new Label(""); //数据1的类型
    private Label data2_t = new Label("");
    private Label data3_t = new Label("");
    private Label data1_m = new Label("");
    private Label data2_m = new Label("");
    private Label data3_m = new Label("");
    private HBox data1 = new HBox();
    private HBox data2 = new HBox();
    private HBox data3 = new HBox();
    private VBox double_area = new VBox();
    private  Button connect_set = new Button("设置");
    private Button connect = new Button("开始连接");
    private VBox menu_area = new VBox();

    //静态控件和静态变量设置
    public static TextArea information_public;

    public void start(Stage mainstage) {
        main_stage=mainstage;
        mainstage.initStyle(StageStyle.TRANSPARENT);
        DrawLineTest d = new DrawLineTest();

        //静态控件传递
        information_public = information;

        root.setId("root");
        // 引入样式
        root.getStylesheets().add(Main_window.class.getResource("resources/css/title.css").toString());
        root.getStylesheets().add(Main_window.class.getResource("resources/css/box.css").toString());
        root.getStylesheets().add(Main_window.class.getResource("resources/css/many.css").toString());
        root.getStylesheets().add(Main_window.class.getResource("resources/css/button.css").toString());
        //顶部

        top.setId("top");
        // 标题栏


        close.setId("winClose");
        close.setOnMousePressed(event -> {

            QuestionExit open = new QuestionExit();
            open.start(new Stage());
        });

        minis.setId("winMin");
        minis.setOnMousePressed(event -> {
            main_stage.setIconified(true);
        });

        maxs.setId("winMax");
        maxs.setOnMousePressed(event -> {

            if ( main_stage.isFullScreen()){
                maxs.setId("winMax");
            main_stage.setFullScreen(false);}
            else {
                main_stage.setFullScreen(true);
                maxs.setId("winMax_2");
            }
        });

        user.setId("winUser");
        user.setOnMousePressed(event -> {
                });

        downtray.setId("winDown");
        downtray.setOnMousePressed(event -> {
            MainTray mt= new MainTray();
            mt.enableTray(main_stage);
        });

        logo.setId("logo");

        tit.setId("tit");
        title.getChildren().addAll(close,minis,user,downtray,logo,tit,maxs);

        AnchorPane.setRightAnchor(close, 5.0);AnchorPane.setTopAnchor(close, 8.0);
        AnchorPane.setRightAnchor(maxs, 30.0);AnchorPane.setTopAnchor(maxs, 8.0);
        AnchorPane.setRightAnchor(minis, 55.0);AnchorPane.setTopAnchor(minis, 8.0);
        AnchorPane.setRightAnchor(downtray, 85.0);AnchorPane.setTopAnchor(downtray, 8.0);
        AnchorPane.setRightAnchor(user , 115.0);AnchorPane.setTopAnchor(user , 9.0);
        AnchorPane.setLeftAnchor(logo, 15.0);AnchorPane.setTopAnchor(logo, 5.0);
        AnchorPane.setLeftAnchor(tit, 50.0);AnchorPane.setTopAnchor(tit, 5.0);
        top.getChildren().add(title);

        // 内容
        content.setId("hbox_content");
        //第一分区
        first_area.setId("box_first_area");
        machine_area.setId("vbox_machine_area");


        list.add(new Data("保定1号", "1", "0","green"));
        list.add(new Data("保定2号", "1", "0","green"));
        list.add(new Data("保定3号", "1", "0","green"));
        list.add(new Data("保定4号", "1", "0","green"));
        list.add(new Data("保定5号", "1", "0","green"));
        list.add(new Data("保定6号", "1", "0","green"));
        list.add(new Data("保定7号", "1", "0","green"));
        list.add(new Data("保定8号", "1", "0","green"));
        list.add(new Data("保定9号", "1", "0","green"));
        list.add(new Data("保定10号", "1", "0","green"));
        list.add(new Data("保定1号", "1", "0","green"));
        list.add(new Data("保定1号", "1", "0","green"));
        list.add(new Data("保定1号", "1", "0","green"));
        list.add(new Data("保定1号", "1", "0","green"));
        list.add(new Data("废弃1号", "0", "0","green"));
        list.add(new Data("废弃2号", "0", "0","green"));
        list.add(new Data("废弃3号", "0", "0","green"));
        list.add(new Data("废弃4号", "0", "0","green"));
        list.add(new Data("废弃5号", "0", "0","green"));
        list.add(new Data("废弃6号", "0", "0","green"));

        //占位符 当listview没有数据时显示占位符
        listview.setPlaceholder(new Label("无数据"));

        listview.prefWidthProperty().bind(select_area.widthProperty());
        //添加一个可观测的列表显示

        listview.setItems(list);
        listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                if(event.getButton() == MouseButton.PRIMARY )
                {
                    int index = listview.getSelectionModel().getSelectedIndex();

                    Data en =list.get(index);
                    String select =en.getSelected();
                    if (select.equals("0")){
                        en.setSelected(String.valueOf(selected_ones.size()+1));
                        selected_ones.add(index);
                        list.set(index,en);
                    } else {
                        //移除取消选中的项
                        for(int i =0;i<selected_ones.size();i++)
                            if (selected_ones.get(i)==index)
                                selected_ones.remove(i);
                        en.setSelected("0");
                        list.set(index,en);
                        //把取消选中项后面的项向前移动
                        /*for(int j =0;j<selected_ones.size();j++)System.out.println(j+":"+selected_ones.get(j));en.setSelected("0");list.set(index,en);System.out.println("长度 ："+selected_ones.size());*/
                        Data en2;
                        for (int i =0;i<selected_ones.size();i++){
                            int x =selected_ones.get(i);  //x为data序号
                            en2 =list.get(x);
                            en2.setSelected(String.valueOf(i+1));
                            list.set(x,en2);
                        } }
                    listview.setItems(list);
                    selected_num.setText("已选择："+selected_ones.size());

                } }});

        listview.setCellFactory(new Callback<ListView<Data>, ListCell<Data>>() {
            Data data = null;
            ListCell<Data> cell;
            @Override
            public ListCell<Data> call(ListView<Data> param) {
                param.setOnEditStart(new EventHandler<ListView.EditEvent<Data>>() {
                    @Override
                    public void handle(ListView.EditEvent<Data> event) {
                        data = param.getItems().get(event.getIndex());
                    }});
                ListCell<Data> listcell  = new ListCell<Data>(){
                    @Override
                    protected void updateItem(Data item, boolean empty) {
                        super.updateItem(item, empty);

                        if(empty == false){
                            HBox hbox = new HBox();hbox.setId("box_select");
                            Label name = new Label(item.getName());
                            Rectangle rect = new Rectangle(20,20);
                            Label select_1 = new Label();select_1.setId("selected");
                            AnchorPane machine_selected = new AnchorPane();
                            //machine_selected.prefWidthProperty().bind(listview.widthProperty());
                            machine_selected.setPrefWidth(150);
                            machine_selected.getChildren().addAll(rect,name,select_1);
                            AnchorPane.setRightAnchor(select_1, 0.0);AnchorPane.setTopAnchor(select_1, 0.0);
                            AnchorPane.setLeftAnchor(rect, 5.0);AnchorPane.setTopAnchor(rect, 0.0);
                            AnchorPane.setLeftAnchor(name, 30.0);AnchorPane.setTopAnchor(name, 0.0);
                            String selected =item.getSelected();
                            if (selected.equals("0"))
                                select_1.setVisible(false);
                            else{
                                select_1.setText(selected);
                                select_1.setVisible(true);
                            }
                            String color;
                            if (item.getAlive().equals("1")) color = "green";
                            else color = "red";
                            rect.setFill(Color.web(color));
                            hbox.getChildren().addAll(machine_selected);
                            this.setGraphic(hbox);
                        } }};
                return listcell;
            }});


        m_title_area.setId("m_title_area");
        m_title.setId("m_title");
        m_title_area.getChildren().add(m_title);
        m1.setId("model_m");m1.setOnMousePressed(event -> {
            listview.setDisable(true);
        });
        m2.setId("model_m");m2.setOnMousePressed(event -> {
            listview.setDisable(false);
        });


        model_area.getChildren().addAll(m1,m2);
        all_cancel.setPrefHeight(30);
        all_cancel.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if ((boolean)newValue==false){
                    //selected_ones.clear();
                    Data en;
                    for (int i =0;i<selected_ones.size();i++){
                        int x =selected_ones.get(i);  //x为data序号
                        en =list.get(x);
                        en.setSelected("0");
                        list.set(x,en);
                    }
                    listview.setItems(list);
                    selected_ones.clear();
                    selected_num.setText("");
                    //System.out.println(selected_ones+" ");
                }else if((boolean)newValue==true){
                    Data en;
                    for (int i =0;i<list.size();i++){//x为data序号
                        en =list.get(i);
                        en.setSelected(String.valueOf(i+1));
                        list.set(i,en);
                        selected_ones.add(i);
                    }
                    listview.setItems(list);

                    selected_num.setText("");
                }
            }
        });

        machine_area.getChildren().addAll(m_title_area,model_area,none1,listview,none2,all_cancel,selected_num);

        function_area.setId("box_function_area");
        first_area.getChildren().addAll(function_area,machine_area);


        function_area_a.getStyleClass().add("function_area_");
        function_area_b.getStyleClass().add("function_area_");
        function_area_c.getStyleClass().add("function_area_");
        function_area_d.getStyleClass().add("function_area_");
        a1.getStyleClass().add("menu-btn");
        a2.getStyleClass().add("menu-btn");a2.setOnMousePressed(event -> {
            a2();
        });
        a3.getStyleClass().add("menu-btn");
        a4.getStyleClass().add("menu-btn");
        a5.getStyleClass().add("menu-btn");
        a4.setOnMousePressed(event -> {a4();});


        function_area_a.getChildren().addAll(a1,a2,a3,a4,a5);
        b1.getStyleClass().add("menu-btn");
        b2.getStyleClass().add("menu-btn");b2.setOnMousePressed(event -> {
        });
        b3.getStyleClass().add("menu-btn");
        b4.getStyleClass().add("menu-btn");
        b5.getStyleClass().add("menu-btn");
        function_area_b.getChildren().addAll(b1,b2,b3,b4,b5);
        c1.getStyleClass().add("menu-btn");
        c2.getStyleClass().add("menu-btn");
        c3.getStyleClass().add("menu-btn");
        c4.getStyleClass().add("menu-btn");
        c5.getStyleClass().add("menu-btn");
        function_area_c.getChildren().addAll(c1,c2,c3,c4,c5);
       d2.getStyleClass().add("menu-btn");
        d1.getStyleClass().add("menu-btn");d1.setOnMousePressed(event -> {
            d1(); });
        function_area_d.getChildren().addAll(d1,d2);







        menu.getStyleClass().add("accordion_a");
        menu.getPanes().addAll(t1,t2,t3,t4);
        g_menu.getChildren().add(menu);
        menu_title.setId("menu_title");
        function_area.getChildren().addAll(menu_title,g_menu);

        //第二分区
        second_area.getStyleClass().add("box_second_area");

        //图像区
        figure_area.setAlignment(Pos.TOP_LEFT);

        figure_area.getStyleClass().add("box_figure_area");



        //图像区—图形区
        Polyline polyline = new Polyline();
        drawline = polyline;

        Polyline polyline_stop = new Polyline();
        co_y = new Line(0,0,0,400);   //y轴
        co_x = new Line(0,400,600,400);   //x轴
        co_x_degree = new Polyline();  //刻度
        co_y_degree = new Polyline();
        Line co_y2 = new Line(600,400,600,0);  //辅助轴
        co_y2.setVisible(false);
        Line alligment = new Line(0,100,600,100);  //校准线
        alli_bottom = new Line(0,100,600,100); //底部校准线
        alli_top = new Line(0,200,600,200);

        co_y.setVisible(false);
        co_x.setVisible(false);
        alli_bottom.setVisible(false);
        alli_top.setVisible(false);

        alli_bottom.setSmooth(false);
        alli_top.setSmooth(false);
        polyline.setSmooth(false);
        alligment.setVisible(false);
       // alli_bottom.setVisible(false);

        Pane figure_center = new Pane();
        VBox figure_center_ext = new VBox();
        //figure_center.setAlignment(Pos.CENTER_RIGHT);
        figure_center.setId("vbox_figure_center");
        figure_center_ext.setId("vbox_figure_center_ext");
        Pane s1 = new Pane();
        StackPane s2 = new StackPane();
        StackPane s = new StackPane();

        figure_center.setOnMouseEntered(event -> {
            alligment.setVisible(true);
        });
        figure_center.setOnMouseExited(event -> {
            alligment.setVisible(false);
        });

        figure_center.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent m) {
            double ssx=m.getSceneX();
            double ssy=m.getSceneY();
            ssy -= 200;
            //if()

            if (ssy>=0){
                alligment.setStartY(ssy);alligment.setEndY(ssy);
                }
            information.setText("fx:"+ssx+" fy:"+ssy);

                }});
        figure_center.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent m) {
            }
        });

        s.setId("vbox_figure_center");
        s1.getChildren().addAll(co_x,co_y,co_y2,co_x_degree,co_y_degree,alligment,alli_bottom,alli_top);
        //s1.setPrefSize(600,400);
        s2.getChildren().addAll(polyline,polyline_stop);
        s2.setAlignment(Pos.CENTER_RIGHT);
        s.getChildren().addAll(s1,s2);

        figure_center.getChildren().addAll(s);
        figure_center_ext.getChildren().add(figure_center);


        //图像工具栏区
       figure_tools.setId("hbox_figure_tools");
        figure_tools.prefWidthProperty().bind(figure_area.widthProperty());
        removeall.setOnMousePressed(event -> {
            d.removeline(polyline);
        });
        stopall.setOnMousePressed(event -> {
            if (polyline.isVisible()){
            stopall.setText("开始");
            d.stopline(polyline,polyline_stop);
            }
            else{ stopall.setText("停止");
                d.startline(polyline,polyline_stop);
            }
        });



        figure_tools.getChildren().addAll(removeall,stopall);
        //figure_area.getChildren().addAll(figure_center_ext,figure_tools);

        figure_area.getChildren().addAll(figure_area_,figure_tools);
        //信息区


        information_area.getStyleClass().add("box_figure_area");

        information.setId("text_area");
        information.setText("");
        information.setEditable(false);
        information_area.getChildren().add(information);
        second_area.getChildren().addAll(figure_area,information_area);


        //第三分区


        //状态区

        condition_area.getStyleClass().add("box_condition_area");
        title1.getStyleClass().add("vbox_condition_title");
        title2.getStyleClass().add("vbox_condition_title");
        content1.getStyleClass().add("vbox_condition_content");
        content2.getStyleClass().add("vbox_condition_content");
        title1l.getStyleClass().add("third_condition_title_label");
        title2l.getStyleClass().add("third_condition_title_label");
        title1.getChildren().add(title1l);
        title2.getChildren().add(title2l);
        condition_first.getChildren().addAll(title1,content1);
        condition_first.setId("vbox_condition_this");
        condition_second.getChildren().addAll(title2,content2);
        condition_second.setId("vbox_condition_all");

        data1.setPrefSize(145,30);
        data2.setPrefSize(145,30);
        data3.setPrefSize(145,30);
        data1.getChildren().addAll(data1_t,data1_m);
        data2.getChildren().addAll(data2_t,data2_m);
        data3.getChildren().addAll(data3_t,data3_m);
        content1.getChildren().addAll(data1,data2,data3);
        condition_area.getChildren().addAll(condition_first,condition_second);


        //双键区
        double_area.getStyleClass().add("box_double_area");
        connect_set.getStyleClass().add("connect_set");
        connect_set.setOnMousePressed(event -> {
            ConnectSet b = new ConnectSet();
            b.start(new Stage());
        });


        connect.getStyleClass().add("connect");
        connect.setOnMousePressed(event -> {
            if(connect.getText().equals("终止连接")){
                client=null;
                connect.setText("开始连接");
                connected_finalize(1);
                return;
            }
            Client cli = new Client();
            if(cli.handler()==1){
                connected_finalize(0);
                connect.setText("终止连接");
            }

        });
        double_area.getChildren().addAll(connect,connect_set);

        //菜单区
        menu_area.getStyleClass().add("box_menu_area");
        third_area.getChildren().addAll(condition_area,double_area,menu_area);
        third_area.getStyleClass().add("box_third_area");
        content.getChildren().addAll(first_area,second_area,third_area);
        content.getStyleClass().add("box_content");

        //初始化
        firstarea = first_area;
        secondarea = figure_area;
        conditionarea = condition_area;
        connected_finalize(1);

        // 组装
        root.getChildren().addAll(top, content);
        Scene scene = new Scene(root);
        mainstage.setScene(scene);
        DragUtil.addDragListener(mainstage, top);
        // 显示
        initialize();
        mainstage.show();
    }
    public void connected_finalize(int what){
        if (what==1){
        firstarea.setDisable(true);
        secondarea.setDisable(true);
            conditionarea.setDisable(true);}
        else{
            firstarea.setDisable(false);
            secondarea.setDisable(false);
            conditionarea.setDisable(false);
        }
    }

    private void initialize(){
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        double height = screensize.getHeight()*0.9;
        double width = height*1.4;
        double first_area_width =width*0.15;
        double first_area_height =height*0.9;
        double second_area_width =width*0.7;
        double  second_area_height=height*0.9;
        double third_area_width =width*0.15;
        double third_area_height =height*0.9;
        content.setPrefSize(width,height);
        root.setPrefSize(width,height);
        //第一分区
        first_area.setPrefSize(first_area_width,first_area_height);
        function_area.setPrefSize(first_area_width,first_area_height*0.4);
        machine_area.setPrefSize(first_area_width,first_area_height*0.6);
        //第二分区
        second_area.setPrefSize(second_area_width,second_area_height);
        figure_area.setPrefSize(second_area_width*0.9,second_area_height*0.8);
        information_area.setPrefSize(second_area_width*0.9,second_area_height*0.2);
        //第三分区
        third_area.setPrefSize(third_area_width,third_area_height);
        condition_area.setPrefSize(third_area_width,third_area_height*0.6);
        double_area.setPrefSize(third_area_width,third_area_height*0.13);
        menu_area.setPrefSize(third_area_width,third_area_height*0.25);
    }


    public void tray(){
        MainTray a = new MainTray();
        a.enableTray(main_stage);
    }

    public void connect(){
    }
    private void d1(){
        if(selected_ones.size()==0)
            return;
        int a=0;
        while(true){
            if(a*a>=selected_ones.size())
                break;
            a++;
        }
        GridPane pane= new GridPane();
        pane.setHgap(a);
        pane.setVgap(a);
        pane.minWidthProperty().bind(figure_area_.widthProperty());
        pane.minHeightProperty().bind(figure_area_.heightProperty());
        for(int i=0;i<a;i++)
            for (int j=0;j<a;j++){
                VBox nn =new VBox();
                nn.getStyleClass().add("box_pane_multi");
                if (i*a+j+1<=selected_ones.size()){
                    int index = selected_ones.get(i*a+j);
                    String name=list.get(index).getName();
                    Label nam=new Label(name);
                    Label tem=new Label("温度:  ");
                    Label wet=new Label("湿度:  ");
                    HBox quality= new HBox();
                    nn.getChildren().addAll(nam,tem,wet);
                }
                //HBox temm= new HBox();temm.getChildren().addAll(tem);temm.setId("box_pane_multi_box");
                //HBox wett= new HBox();wett.getChildren().addAll(wet);wett.setId("box_pane_multi_box");
                GridPane.setHgrow(nn,Priority.ALWAYS);
                GridPane.setVgrow(nn,Priority.ALWAYS);
                nn.setPrefSize(300,200);
                pane.add(nn,i,j);
            }
        figure_area_.getChildren().clear();
        figure_area_.getChildren().add(pane);
    }
    private void a2(){


        //时间戳>数据类型>机器数>机号

            long time = System.currentTimeMillis();
            String request_type="heat2picture";
            String machine_numbers="1";
            String machine_name="BaoDing1";
            String message =time+">"+request_type+">"+machine_numbers+">"+machine_name;
        new Thread(new Client_Writer(client,message)).start();
        //new Thread(new ClientWriteHandlerThread(client,message)).start();
        if(selected_ones.size()==0)
            return;
        int a=0;
        while(true){
            if(a*a>=selected_ones.size())
                break;
            a++;
        }
        GridPane pane= new GridPane();
        pane.setHgap(a);
        pane.setVgap(a);
        //pane.maxWidthProperty().bind(figure_area_.widthProperty());
        //.pane.maxHeightProperty().bind(figure_area_.heightProperty());

        for(int i=0;i<a;i++)
            for (int j=0;j<a;j++){
                VBox nn =new VBox();
                nn.getStyleClass().add("box_pane_multi");
                if (i*a+j+1<=selected_ones.size()){
                    int index = selected_ones.get(i*a+j);
                    String name=list.get(index).getName();
                    Label nam=new Label(name);
                    Image image = new Image("file:D:\\heat2picture\\Inter_test\\final.png");
                    ImageView imageView = new ImageView();
                    heat_picture=imageView;
                    imageView.setImage(image);
                    //imageView.setId("imageview");
                    imageView.setPreserveRatio(true);

                    System.out.println(pane.getHeight());
                    System.out.println(pane.getPrefWidth());
                    imageView.setFitWidth(figure_area_.getWidth()/a);
                    imageView.setFitHeight(figure_area_.getHeight()/a);
                    //imageView.fitHeightProperty().bind(pane.heightProperty());
                    //imageView.fitWidthProperty().bind(pane.widthProperty());
                    nn.getChildren().addAll(nam,imageView);
                }
                //HBox temm= new HBox();temm.getChildren().addAll(tem);temm.setId("box_pane_multi_box");
                //HBox wett= new HBox();wett.getChildren().addAll(wet);wett.setId("box_pane_multi_box");
                GridPane.setHgrow(nn,Priority.ALWAYS);
                GridPane.setVgrow(nn,Priority.ALWAYS);
                nn.setPrefSize(300,200);
                pane.add(nn,i,j);
            }
        figure_area_.getChildren().clear();
        figure_area_.getChildren().add(pane);
        /*
        VBox pane = new VBox();
        Image image = new Image("file:D:\\heat2picture\\Inter_test\\final.png");
        ImageView imageView = new ImageView();
       imageView.setImage(image);
        pane.getChildren().addAll(imageView);
        figure_area_.getChildren().clear();
        figure_area_.getChildren().add(pane);

         */
    }

    public void a4(){
        try{
        DrawLineTest d= new DrawLineTest();
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {
                co_y.setVisible(true);
                co_x.setVisible(true);
                alli_bottom.setVisible(true);
                alli_top.setVisible(true);
                d.randomline(drawline, tempmaxi,tempnowi,co_x_degree,co_x,xs,information,alli_bottom,alli_top);
                return null; }};
        Task<Void> task_coord = new Task<Void>() {
                @Override
                public Void call() {
                    d.aa(co_x_degree,co_y_degree,co_x,co_y,xs);
                    return null; }};
        if (being.equals("")) {
            new Thread(task_coord).start();
            new Thread(task).start();
            being = "tem_test";
        } else if (being.equals("tem_test")) {
        } else {
            being = "tem_test"; }; }
        catch (Exception e ){
            e.printStackTrace();
        }
        }
    public static void main(String[] args) {
        launch(args);
    }
}
class Data{

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty alive = new SimpleStringProperty();
    private SimpleStringProperty selected = new SimpleStringProperty();
    private SimpleStringProperty none = new SimpleStringProperty();
    public Data(String name, String alive, String selected,String none){
        this.name.set(name);
        this.alive.set(alive);
        this.selected.set(selected);
        this.none.set(none);
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }
    public void setName(String _new){this.name.set(_new);}
    public String getName(){
        return name.get();
    }

    public SimpleStringProperty getAliveProperty() {
        return alive;
    }
    public String getAlive(){
        return alive.get();
    }
    public void setAlive(String _new){this.alive.set(_new);}

    public SimpleStringProperty getSelectedProperty() {
        return selected;
    }
    public void setSelected(String _new){this.selected.set(_new);}
    public String getSelected(){
        return selected.get();
    }
    public SimpleStringProperty getNoneProperty() {
        return none;
    }
    public String getNone(){
        return none.get();
    }
    @Override
    public String toString() {
        return "name=" + name + ", alive=" + alive + ", selected=" + selected+", none="+none;
    }

}

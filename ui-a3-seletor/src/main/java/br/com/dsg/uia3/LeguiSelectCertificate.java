package br.com.dsg.uia3;

import org.liquidengine.legui.system.context.Context;

import br.com.dsg.legui.controller.StartLeGui;

/**
 * Created by Aliaksandr_Shcherbin on 1/25/2017.
 */
public class LeguiSelectCertificate {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private static volatile boolean running = false;
    private static long[] monitors = null;
    private static boolean toggleFullscreen = false;
    private static boolean fullscreen = false;
    private static SelecaoA3Gui gui;
    private static Context context;

//    private static String json = IOUtil.loadResourceAsString("org/liquidengine/legui/demo/json.json", 1024);

    public static void main(String[] args) {
       StartLeGui.get(800, 600, "Seleção Certificado A3")
       .addItemActionMenu(
				"",
				"imagens/icons8-cardapio-30.png",
				"imagens/icons8-cardapio-fechado-30.png", 
				true, 
				true,
				(c) -> c.menuAbrirFecharMenu() 
       )
       .addItemMenu(
    		   "Selecionar Certificado", 
    		   "img/pendrive.png", 
    		   (controllerPai)-> new ControllerSelecaoA3(controllerPai),
    		   true
       ).start();
    }



}

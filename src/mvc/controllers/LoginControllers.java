package mvc.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.models.Menu_models;
import mvc.models.Usuario_models;
import mvc.vo.Menu_vo;
import mvc.vo.User_vo;
import mvc.vo.Usuario_vo;

/**
 * Servlet implementation class LoginControllers
 */
@WebServlet("/LoginControllers")
public class LoginControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControllers() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
			String metodo=request.getParameter("metodo");
			if(metodo.equalsIgnoreCase("logueo"))
				Logueo_post(request,response);
			
			else if(metodo.equalsIgnoreCase("Logout"))
				Logout_post(request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void Logueo_post(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			Usuario_vo datosusuario = new Usuario_vo();			
			Usuario_vo beanusario = new Usuario_vo();
			beanusario.setUsuario(request.getParameter("username"));
			beanusario.setPassword(request.getParameter("password"));			
			datosusuario = new Usuario_models().ValidarUsuario(beanusario);
			
			
			if (datosusuario == null){
				request.setAttribute("validaLogin",1)/*Usuario y password incorrecto*/;
				rd = request.getRequestDispatcher("/index.jsp");
			}else{
				
				if (datosusuario.getTrabajador().getEstado()==0){
					request.setAttribute("validaLogin",2)/*usuario se encuentra bloqueado*/;
					rd = request.getRequestDispatcher("/index.jsp");
				}else{
					
					ArrayList<Menu_vo> list = new ArrayList<Menu_vo>();
					list = new Menu_models().ListarMenu(beanusario);
					HttpSession sesion =request.getSession(true);	
					sesion.setAttribute("listadomenu",list);
					sesion.setAttribute("beanusario",datosusuario);
					
					//request.setAttribute("beanusario", beanusario);	
					rd = request.getRequestDispatcher("/Principal.jsp");
				}
			}
			
			/*
			if (result.equals("success")) {
				rd = request.getRequestDispatcher("/success.jsp");
				User_vo user = new User_vo(username, password);
				request.setAttribute("user", user);
			} else {
				rd = request.getRequestDispatcher("/error.jsp");
			}*/
			

		} catch (Exception e) {
			request.setAttribute("validaLogin",3)/*Ocurrio error al ingresar al sistema*/;
			rd = request.getRequestDispatcher("/index.jsp");
		}
		rd.forward(request, response);
	}

	private void Logout_post(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	
}

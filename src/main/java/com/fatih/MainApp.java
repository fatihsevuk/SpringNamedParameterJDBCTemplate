package com.fatih;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fatih.model.Kisi;
import com.fatih.service.KisiDAOService;

public class MainApp {
	
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("app-config.xml");
		
		KisiDAOService kisiService=context.getBean("kisiDAOServiceId",KisiDAOService.class);
		
		Kisi kisi=kisiService.kisiOlustur(78, "ihsan", 26);
		
		//kisiService.kisiEkle(kisi);
		
		//kisiService.kisiBulById(23);
		
		//kisiService.kisiSilById(9);
		
		//kisi.setAd("murat");
		//kisi.setYas(45);
		//kisiService.guncelle(kisi);
		
		kisiService.tumKisileriListele();
		
	}

}

package com.fatih.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.fatih.dao.KisiDAO;
import com.fatih.dao.KisiRowMapper;
import com.fatih.model.Kisi;

public class KisiDAOImpl implements KisiDAO{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private final static String KISI_EKLE = "insert into kisiler (kisiId, ad,yas) values(:kisiId,:ad,:yas)";
	private final static String KISI_BUL = "select * from kisiler where kisiId=:kisiId";
	private final static String TUM_KISILERI_LISTELE = "select * from kisiler";
	private final static String KISI_GUNCELLE = "update kisiler set ad=:ad , yas=:yas where kisiId=:kisiId";
	private final static String KISI_SIL = "delete from kisiler where kisiId=:kisiId";

	public void setDataSource(DataSource dataSource){
		namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
	}
	
	
	public void kisiEkle(Kisi kisi) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("kisiId", kisi.getKisiId());
		params.put("ad", kisi.getAd());
		params.put("yas", kisi.getYas());
		
		namedParameterJdbcTemplate.update(KISI_EKLE, params);
		// logging
		System.out.println("kisi is inserted... " + kisi);
		
	}

	public Kisi kisiBulById(int kisiId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("kisiId", kisiId);
		Kisi kisi = namedParameterJdbcTemplate.queryForObject(KISI_BUL, namedParameters, new KisiRowMapper());
		// logging
		System.out.println("kisi is found... " + kisi);
		return kisi;
	}

	public List<Kisi> tumKisileriListele() {
		List<Kisi> kisiList = namedParameterJdbcTemplate.query(TUM_KISILERI_LISTELE, new KisiRowMapper());
		// logging
		System.out.println("kisi list...");
		for (Kisi kisi : kisiList) {
			System.out.println(kisi);
		}
		return kisiList;
	}

	public void guncelle(Kisi kisi) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(kisi);
		namedParameterJdbcTemplate.update(KISI_GUNCELLE, params);
		// logging
		System.out.println("kisi is updated... " + kisi);
		
	}

	public void kisiSilById(int kisiId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("kisiId", kisiId);
		namedParameterJdbcTemplate.update(KISI_SIL, namedParameters);
		// logging
		System.out.println("kisi is deleted... Id :" + kisiId);
		
	}
	
	
}

package com.nc.jpa_exercise1;

import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="photo_name")
	private String photo_name;
	
	@Column(name="photo_data")
	@Lob
	private byte[] photo_data;

	public static String getPhotoData(byte[] photo_data) {
        if(photo_data!=null && photo_data.length>0) {
            return Base64.getEncoder().encodeToString(photo_data);
        }
        else
            return "";
    }

	
}

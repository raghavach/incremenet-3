package com.example.bussinessanalsis;

import java.io.Serializable;

public class ItemsDetails implements Serializable {
 String	discrption,price,tag,note,date,sppinervalue,status;

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getDiscrption() {
	return discrption;
}

public void setDiscrption(String discrption) {
	this.discrption = discrption;
}

public String getPrice() {
	return price;
}

public String setPrice(String price) {
	return this.price = price;
}

public String getTag() {
	return tag;
}

public String setTag(String setTag) {
	return this.tag = setTag;
}

public String getNote() {
	return note;
}

public String setNote(String note) {
	return this.note = note;
}

public String getDate() {
	return date;
}

public String setDate( String Date) {
	return this.date = Date;
}

public String getSppinervalue() {
	return sppinervalue;
}

public String setSppinervalue(String spinnerval) {
	return this.sppinervalue=spinnerval;
}

}

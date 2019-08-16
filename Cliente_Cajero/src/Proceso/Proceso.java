package Proceso;

public class Proceso {

	private Integer option;
	private String account_number;
	private Long value;
	
	public Proceso(Integer option, String account_number) {
		this.option = option;
		this.account_number = account_number;
	}
	
	public Proceso(Integer option, String account_number, Long value) {
		this.option = option;
		this.account_number = account_number;
		this.value = value;
	}
	
	public Integer getOption() {
		return option;
	}
	public void setOption(Integer option) {
		this.option = option;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
	public String toString() {
		return this.option+","+this.account_number+","+this.value;
	}
	
}

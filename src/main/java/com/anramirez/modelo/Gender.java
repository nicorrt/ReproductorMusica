package com.anramirez.modelo;

public class Gender {
	
	protected Long idGender;
	protected String name;
	
	public Gender() {
		super();
	}

	public Gender(Long idGender, String name) {
		super();
		this.idGender = idGender;
		this.name = name;
	}

	public Gender(String nombre) {
		this.name=nombre;
		// TODO Auto-generated constructor stub
	}

	public Gender(long idgender) {
		// TODO Auto-generated constructor stub
		this.idGender=idgender;
	}

	public Long getIdGender() {
		return idGender;
	}

	public void setIdGender(Long idGender) {
		this.idGender = idGender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGender == null) ? 0 : idGender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gender other = (Gender) obj;
		if (idGender == null) {
			if (other.idGender != null)
				return false;
		} else if (!idGender.equals(other.idGender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
		/*return "Gender [idGender=" + idGender + ", name=" + name + "]";*/
	}
	
	
	
}

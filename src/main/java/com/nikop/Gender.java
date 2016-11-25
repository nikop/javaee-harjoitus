package com.nikop;

public enum Gender {
	MALE ("Male"),
	FEMALE ("Female");
	
	private final String text;
    
    private Gender(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}

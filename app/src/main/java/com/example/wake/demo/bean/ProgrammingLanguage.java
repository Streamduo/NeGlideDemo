package com.example.wake.demo.bean;

public class ProgrammingLanguage {

    private int id;
    private String name;
    private String content;
    private static volatile ProgrammingLanguage programmingLanguage = null;

    public static ProgrammingLanguage getInstance(){
         if (programmingLanguage == null){
             synchronized (ProgrammingLanguage.class){
                 if (programmingLanguage == null){
                     programmingLanguage = new ProgrammingLanguage();
                     return programmingLanguage;
                 }
             }
         }
         return null;
    }

    public ProgrammingLanguage() {
    }

    public ProgrammingLanguage(ProgrammingLanguageBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.content = builder.content;
    }

    public ProgrammingLanguage setId(int id) {
        this.id = id;
        return this;
    }

    public ProgrammingLanguage setName(String name) {
        this.name = name;
        return this;
    }

    public ProgrammingLanguage setContent(String content) {
        this.content = content;
        return this;
    }

    public static class ProgrammingLanguageBuilder {

        private int id;
        private String name;
        private String content;

        public ProgrammingLanguageBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ProgrammingLanguageBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProgrammingLanguageBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public ProgrammingLanguage build() {
            return new ProgrammingLanguage(this);
        }

    }


}

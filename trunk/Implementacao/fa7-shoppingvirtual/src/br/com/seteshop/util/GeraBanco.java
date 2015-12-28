package br.com.seteshop.util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

public class GeraBanco {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure();
		SchemaUpdate update = new SchemaUpdate(cfg);
		update.execute(true, true);
		//SchemaExport export = new SchemaExport(cfg);
		//export.create(true, true);
	}

}

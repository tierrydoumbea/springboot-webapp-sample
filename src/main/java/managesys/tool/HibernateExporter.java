package managesys.tool;

import java.io.File;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Action;
import org.hibernate.tool.schema.TargetType;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @see https://stackoverflow.com/questions/33700123/hibernate-5-generate-sql-ddl-into-file
public class HibernateExporter {

	private static final Logger LOG = LoggerFactory.getLogger(HibernateExporter.class);
    private static final String OUTPUT_FILE = "schema.sql";
    private static final String DIALECT = "org.hibernate.dialect.H2Dialect";
    private static final String ENTITY_PACKAGE = "managesys.model";

    private List<String> entityPackages;

    private HibernateExporter(List<String> entityPackages) {
        this.entityPackages = entityPackages;
    }

    public static void main(String[] args) {
    	new File(OUTPUT_FILE).delete();

        final List<String> entityPackages = Collections.singletonList(ENTITY_PACKAGE);

        HibernateExporter exporter = new HibernateExporter(entityPackages);
        exporter.export();
    }

    private void export() {
        SchemaExport export = new SchemaExport();
        export.setOutputFile(OUTPUT_FILE);
        export.setFormat(true);
        export.setDelimiter(";");
        EnumSet<TargetType> types = EnumSet.of(TargetType.SCRIPT);
        Metadata metadata = createMetadataSources().buildMetadata();
        export.execute(types, Action.BOTH, metadata);
    }

    private MetadataSources createMetadataSources() {
        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.dialect", DIALECT)
                        .build());

        for (String entityPackage : entityPackages) {
            final Reflections reflections = new Reflections(entityPackage);
            for (Class<?> cl : reflections.getTypesAnnotatedWith(MappedSuperclass.class)) {
                metadata.addAnnotatedClass(cl);
                LOG.info(String.format("Mapped = %s", cl.getName()));
            }
            for (Class<?> cl : reflections.getTypesAnnotatedWith(Entity.class)) {
                metadata.addAnnotatedClass(cl);
                LOG.info(String.format("Mapped = %s", cl.getName()));
            }
        }
        return metadata;
    }
}

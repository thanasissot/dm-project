package sotiroglou.athanasios.microservices.catalogue;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ImageDirectoryProducer {

    @ConfigProperty(name = "quarkus.profile")
    String profile;

    @ConfigProperty(name = "image.dir")
    String imageDir;

    public String getImagesDirectory() {
        return imageDir;
    }

    public boolean isRunningOnDocker() {
        return profile.equals("docker");
    }
}

package sotiroglou.athanasios.microservices;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.springframework.beans.factory.annotation.Value;

@ApplicationScoped
public class ImageDirectoryProducer {

    @ConfigProperty(name = "quarkus.profile")
    String profile;

    @Value("${image.dir}")
    String IMAGE_DIR;

    @ConfigProperty(name = "image.dir")
    String imageDir;

    public String getImagesDirectory() {
        return imageDir;
    }

    public boolean isRunningOnDocker() {
        return profile.equals("docker");
    }
}

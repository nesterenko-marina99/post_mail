package media.soft.post_mail.domain.post_office;

public class PostOfficeService {

    PostOfficeRepository postOfficeRepository;

    public PostOffice createOffice(PostOffice postOffice) {
        return postOfficeRepository.save(postOffice);
    }


}

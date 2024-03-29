package begin.flywayspringmaven.common.service.aws;

import begin.flywayspringmaven.common.vo.PresignedURL;

public interface IAwsService {
    /**
     * Create pre signed url for PUT method
     *
     * @param fileName fileName
     * @return pre sign url
     */
    PresignedURL uploadPreSignedUrl(String fileName);
}

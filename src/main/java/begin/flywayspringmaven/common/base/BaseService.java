package begin.flywayspringmaven.common.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BaseService {

    @Value("${app.paging.default.pageInit}")
    protected int pagingDefaultPageInit;

    @Value("${app.paging.default.limit}")
    protected int pagingDefaultLimit;

    /**
     * Build {@link PageRequest} with page and limit
     *
     * @param page
     * @param limit
     * @param sort
     * @return
     */
    public PageRequest buildPageRequest(Integer page, Integer limit, Sort sort) {
        page = page == null ? 0 : page - this.pagingDefaultPageInit;
        limit = limit == null ? this.pagingDefaultLimit : limit;
        return PageRequest.of(page, limit);
    }
}

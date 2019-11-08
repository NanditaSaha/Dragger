package app.ceva.petapp.data.network.model.Allpages;

public class AllpagesRequest {


    /**
     * page_slug : contact-us
     */

    private String page_slug;

    public AllpagesRequest(String page_slug) {
        this.page_slug = page_slug;
    }

    public String getPage_slug() {
        return page_slug;
    }

    public void setPage_slug(String page_slug) {
        this.page_slug = page_slug;
    }
}

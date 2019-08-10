package pl.braintelligence.requirement.task.domain.news;

import java.util.List;

import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;
import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode;
import pl.braintelligence.requirement.task.domain.exceptions.InvalidEntityException;
import pl.braintelligence.requirement.task.domain.values.Article;
import pl.braintelligence.requirement.task.domain.values.Category;
import pl.braintelligence.requirement.task.domain.values.Country;

import static pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode.NONEXISTENT_CATEGORY;
import static pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode.NONEXISTENT_COUNTRY;
import static pl.braintelligence.requirement.task.domain.exceptions.utils.PreCondition.when;

public class News {

    private Country country;
    private Category category;
    private List<Article> articles;

    public News() {
    }

    public News(Country country, Category category) {
        validateCountry(country);
        validateCategory(category);
        this.country = country;
        this.category = category;
    }

    private void validateCategory(Category category) {
        when(category.isInvalid())
                .thenInvalidEntity(NONEXISTENT_CATEGORY, "Provided category is invalid.");
    }

    private void validateCountry(Country country) {
        when(country.isInvalid())
                .thenInvalidEntity(NONEXISTENT_COUNTRY, "Provided country is invalid.");
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


}


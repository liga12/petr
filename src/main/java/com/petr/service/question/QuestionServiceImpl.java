package com.petr.service.question;

import com.petr.exception.QuestionExistsException;
import com.petr.exception.QuestionMinMaxException;
import com.petr.exception.QuestionNotFoundException;
import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.QuestionType;
import com.petr.persistence.entity.Survey;
import com.petr.persistence.repository.QuestionRepository;
import com.petr.service.survey.SurveyService;
import com.petr.transport.dto.question.QuestionCreateDto;
import com.petr.transport.dto.question.QuestionFindDto;
import com.petr.transport.dto.question.QuestionOutcomeDto;
import com.petr.transport.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl extends QuestionSearchSpecification implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    private SurveyService surveyService;

    @Autowired
    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }


    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Question getById(Long id){
        return questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public Page<QuestionOutcomeDto> getAll(QuestionFindDto dto, Pageable pageable) {
        Page<Question> result = questionRepository.findAll(
                questionFilter(dto),
                pageable
        );
        return result.map(questionMapper::toDto);
    }

    @Override
    public Long create(QuestionCreateDto dto, Long surveyId) {
        validateQuestion(dto, surveyId);
        Survey survey = surveyService.getById(surveyId);
        Question question = questionMapper.toEntity(dto);
        question.setSurvey(survey);
        return questionRepository.save(question).getId();
    }

    private void validateQuestion(QuestionCreateDto dto, Long surveyId) {
        if (questionRepository.existsByTextAndSurveyId(dto.getText(), surveyId)) {
            throw new QuestionExistsException();
        }
        if (dto.getMin()>dto.getMax()){
            throw  new QuestionMinMaxException();
        }
        if (dto.getType().equals(QuestionType.TEXT)){
            if (dto.getMin()>1&&dto.getMax()>1){
                throw new QuestionExistsException();
            }
        }
    }

    @Override
    public List<Long> getIdFromEntity(List<Question> questions) {
        if (questions == null) {
            return null;
        }
        List<Long> userIds = new ArrayList<>();
        for (Question question : questions) {
            userIds.add(question.getId());
        }
        return userIds;
    }
}

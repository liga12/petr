package com.petr.service.answer;

import com.petr.exception.answer.AnswerExistsException;
import com.petr.exception.answer.AnswerNotFoundException;
import com.petr.exception.question.QuestionCanNotHasAnswerException;
import com.petr.exception.question.QuestionDeletedException;
import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.QuestionType;
import com.petr.persistence.entity.Status;
import com.petr.persistence.repository.AnswerRepository;
import com.petr.service.question.QuestionService;
import com.petr.transport.dto.answer.AnswerCreateDto;
import com.petr.transport.dto.answer.AnswerFindDto;
import com.petr.transport.dto.answer.AnswerOutcomeDto;
import com.petr.transport.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl extends AnswerSearchSpecification implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    private QuestionService questionService;

    @Autowired
    public void setSurveyService(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public Answer getById(Long id){
        return answerRepository.findById(id).orElseThrow(AnswerNotFoundException::new);
    }

    @Override
    public Page<AnswerOutcomeDto> getAll(AnswerFindDto dto, Pageable pageable) {
        Page<Answer> result = answerRepository.findAll(
                answerFilter(dto),
                pageable
        );
        return result.map(answerMapper::toDto);
    }

    @Override
    public Long create(AnswerCreateDto dto, Long questionId) {
        validateAnswer(dto, questionId);
        Question question = questionService.getById(questionId);
        if (question.getType().equals(QuestionType.TEXT)){
            throw new QuestionCanNotHasAnswerException();
        }
        Answer answer = answerMapper.toEntity(dto);
        answer.setQuestion(question);
        return answerRepository.save(answer).getId();
    }

    @Override
    public List<Long> getIdFromEntity(List<Answer> answers) {
        if (answers == null) {
            return null;
        }
        List<Long> answerIds = new ArrayList<>();
        for (Answer answer : answers) {
            answerIds.add(answer.getId());
        }
        return answerIds;
    }

    @Override
    public void setStatus(Long id, Status status){
        getById(id).setStatus(status);
    }


    private void validateAnswer(AnswerCreateDto dto, Long questionId) {
        if (questionService.getById(questionId).getStatus().equals(Status.DELETED)){
            throw new QuestionDeletedException();
        }
        if (answerRepository.existsByTextAndQuestionId(dto.getText(), questionId)) {
            throw new AnswerExistsException();
        }
    }
}

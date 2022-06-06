package br.com.razes.bytecode.controller.transactions;

import br.com.razes.bytecode.model.coin.CoinType;
import br.com.razes.bytecode.model.transactions.Transactions;
import br.com.razes.bytecode.model.transactions.dto.TransactionsDTO;
import br.com.razes.bytecode.security.TokenService;
import br.com.razes.bytecode.service.transactions.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TransactionsService transactionsService;

    @GetMapping
    public Page<TransactionsDTO> allUserTransaction(@RequestHeader(value = "Authorization") String token,
        @PageableDefault(sort="transactionsCoinType", direction = Sort.Direction.ASC, page = 0, size=3) Pageable pagination) {

        Long idUser = tokenService.getIdUser(token.substring(7));

        Page<Transactions> transactions = transactionsService.getTransactionsByIdUser(idUser,pagination);

        return TransactionsDTO.converter(transactions);
    }


    @GetMapping("/{type}")
    public ResponseEntity<TransactionsDTO> userTransactionByType(@RequestHeader(value = "Authorization") String token,
                                   @PathVariable(value = "type") int coinType) {

        Long idUser = tokenService.getIdUser(token.substring(7));
        CoinType type = CoinType.valueOf(coinType);
        Transactions transactions = transactionsService.getTransactionsBy(idUser,type);

        return ResponseEntity.ok(new TransactionsDTO(transactions));
    }
}

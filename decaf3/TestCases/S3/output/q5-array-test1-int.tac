VTABLE(_Main) {
    <empty>
    Main
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T0 = 4
    parm _T0
    _T1 =  call _Alloc
    _T2 = VTBL <_Main>
    *(_T1 + 0) = _T2
    return _T1
}

FUNCTION(main) {
memo ''
main:
    _T4 = 3
    _T5 = 6
    _T6 = 0
    _T7 = (_T5 < _T6)
    if (_T7 == 0) branch _L10
    _T8 = "Decaf runtime error: The length of the created array should not be less than 0.\n"
    parm _T8
    call _PrintString
    call _Halt
_L10:
    _T9 = 4
    _T10 = (_T9 * _T5)
    _T11 = (_T9 + _T10)
    parm _T11
    _T12 =  call _Alloc
    *(_T12 + 0) = _T5
    _T13 = 0
    _T12 = (_T12 + _T11)
_L11:
    _T11 = (_T11 - _T9)
    _T14 = (_T13 < _T11)
    if (_T14 == 0) branch _L12
    _T12 = (_T12 - _T9)
    *(_T12 + 0) = _T4
    branch _L11
_L12:
    _T3 = _T12
    _T15 = 1
    _T16 = *(_T3 - 4)
    _T17 = (_T15 < _T16)
    if (_T17 == 0) branch _L13
    _T18 = 0
    _T19 = (_T15 < _T18)
    if (_T19 == 0) branch _L14
_L13:
    _T20 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T20
    call _PrintString
    call _Halt
_L14:
    _T21 = 4
    _T22 = (_T15 * _T21)
    _T23 = (_T3 + _T22)
    _T24 = *(_T23 + 0)
    _T25 = 1
    _T26 = *(_T3 - 4)
    _T27 = (_T25 < _T26)
    if (_T27 == 0) branch _L15
    _T28 = 0
    _T29 = (_T25 < _T28)
    if (_T29 == 0) branch _L16
_L15:
    _T30 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T30
    call _PrintString
    call _Halt
_L16:
    _T31 = 4
    _T32 = (_T25 * _T31)
    _T33 = (_T3 + _T32)
    _T34 = *(_T33 + 0)
    _T35 = 1
    _T36 = (_T34 + _T35)
    _T37 = 4
    _T38 = (_T15 * _T37)
    _T39 = (_T3 + _T38)
    *(_T39 + 0) = _T36
    _T40 = 0
    _T41 = *(_T3 - 4)
    _T42 = (_T40 < _T41)
    if (_T42 == 0) branch _L17
    _T43 = 0
    _T44 = (_T40 < _T43)
    if (_T44 == 0) branch _L18
_L17:
    _T45 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T45
    call _PrintString
    call _Halt
_L18:
    _T46 = 4
    _T47 = (_T40 * _T46)
    _T48 = (_T3 + _T47)
    _T49 = *(_T48 + 0)
    parm _T49
    call _PrintInt
    _T50 = "\n"
    parm _T50
    call _PrintString
    _T51 = 1
    _T52 = *(_T3 - 4)
    _T53 = (_T51 < _T52)
    if (_T53 == 0) branch _L19
    _T54 = 0
    _T55 = (_T51 < _T54)
    if (_T55 == 0) branch _L20
_L19:
    _T56 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T56
    call _PrintString
    call _Halt
_L20:
    _T57 = 4
    _T58 = (_T51 * _T57)
    _T59 = (_T3 + _T58)
    _T60 = *(_T59 + 0)
    parm _T60
    call _PrintInt
    _T61 = "\n"
    parm _T61
    call _PrintString
}

